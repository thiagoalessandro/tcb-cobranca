package br.com.totvs.tcb.cobranca.controller.handler;

import br.com.totvs.tcb.cobranca.domain.DominioRecurso;
import br.com.totvs.tcb.cobranca.model.LogTransacional;
import br.com.totvs.tcb.cobranca.utils.HttpReqRespUtils;
import br.com.totvs.tcb.cobranca.utils.LogUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

import static br.com.totvs.tcb.cobranca.utils.LogUtils.*;

@Log4j2
public class RequestInterceptorHandler implements HandlerInterceptor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        try {
            LogUtils.adicionarCdTransacaoContextoMDC(null, LOG_API_CONTEXTO);
            log.debug(":::::::::::::::::REQUEST:::::::::::::::::");
            log.debug("Path: {}", request.getRequestURI());
            log.debug("Method: {}", request.getMethod());
            log.debug("Query {}", request.getQueryString());
        } catch (Exception e) {
            log.error(e);
        }
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) {
        log.debug(":::::::::::::::::RESPONSE:::::::::::::::::");
        log.debug("Status: {}", response.getStatus());
        log.debug("Path: {}", request.getRequestURI());
        log.debug("Method: {}", request.getMethod());
        log.debug("Query {}", request.getQueryString());
        this.createLogTransacional(request, response);
        LogUtils.limparContextoMDC();
    }

    public void createLogTransacional(HttpServletRequest request, HttpServletResponse response) {
        try {
            String transactionDomain = LogUtils.getTransaction(TRANSACTION_RESOURCE);
            String transactionId = LogUtils.getTransaction(TRANSACTION_ID);
            String transactionResponseErrors = LogUtils.getTransaction(TRANSACTION_RESPONSE_ERRORS);
            String transactionException = LogUtils.getTransaction(TRANSACTION_EXCEPTION);
            String transactionTimeBegin = LogUtils.getTransaction(TRANSACTION_TIME_BEGIN);
            String transactionRequestId = LogUtils.getTransaction(TRANSACTION_REQUESTID);
            String transactionResponse = LogUtils.getTransaction(TRANSACTION_RESPONSE);

            LogTransacional logTransacional = LogTransacional.builder()
                    .dominio(transactionDomain != null ? DominioRecurso.valueOf(LogUtils.getTransaction(TRANSACTION_RESOURCE)) : null)
                    .chave(transactionId != null ? Long.valueOf(transactionId) : null)
                    .exception(transactionException)
                    .traceId(Long.valueOf(Objects.requireNonNull(getCodigoTransacao())))
                    .requestId(transactionRequestId)
                    .requestPath(request.getRequestURI())
                    .requestQuery(request.getQueryString())
                    .requestMethod(request.getMethod())
                    .responseErrors(transactionResponseErrors)
                    .responseStatus(response.getStatus())
                    .response(transactionResponse)
                    .ip(HttpReqRespUtils.getClientIpAddressIfServletRequestExist())
                    .dataHoraInicio(transactionTimeBegin != null ? new Date(Long.parseLong(transactionTimeBegin)) : null)
                    .dataHoraFim(new Date())
                    .usuario("DEFAULT")
                    .build();

            logTransacional.setTempoRespostaMs(calcularTempoResposaMilisegundos(logTransacional.getDataHoraInicio(), logTransacional.getDataHoraFim()));
            applicationEventPublisher.publishEvent(logTransacional);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public Integer calcularTempoResposaMilisegundos(Date dataHoraInicio, Date dataHoraFim) {
        if (dataHoraInicio != null && dataHoraFim != null) {
            long tempoResposta = dataHoraFim.getTime() - dataHoraInicio.getTime();
            return (int) tempoResposta;
        }
        return null;
    }

}
