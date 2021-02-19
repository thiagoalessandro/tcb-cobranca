package br.com.totvs.tcb.cobranca.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;

import java.util.Date;
import java.util.Random;

@Log4j2
public class LogUtils {

    public static final String LOG_MSG_CONTEXTO = "msg";
    public static final String LOG_API_CONTEXTO = "api";
    public static final String LOG_SCHEDULER_CONTEXTO = "scheduler";
    private static final String LOG_CD_TRANSACAO = "cdTransacao";
    private static final String LOG_CONTEXTO = "contexto";

    public static final String TRANSACTION_RESOURCE = "transactionResource";
    public static final String TRANSACTION_RESPONSE_ERRORS = "transactionErros";
    public static final String TRANSACTION_ID = "transactionId";
    public static final String TRANSACTION_EXCEPTION = "transactionException";
    public static final String TRANSACTION_TIME_BEGIN = "transactionTimeBegin";
    public static final String TRANSACTION_REQUESTID = "transactionRequestId";
    public static final String TRANSACTION_RESPONSE = "transactionResponse";

    public static String gerarIdentificacaoUnica() {
        long milisegundos = System.nanoTime();
        int valorRandomicoMaximo = 9;
        int numeroRandomico = new Random().ints(0, valorRandomicoMaximo).findFirst()
                .orElse(new Random().nextInt(valorRandomicoMaximo));
        return String.format("%d%01d", milisegundos, numeroRandomico);
    }

    public static void adicionarCdTransacaoContextoMDC(Object cdTransacao, String contexto) {
        try {
            String codigoTransacao = cdTransacao != null ? cdTransacao.toString() : gerarIdentificacaoUnica();
            MDC.put(LogUtils.LOG_CD_TRANSACAO, String.format("[%s]", codigoTransacao));
            if (Strings.isNotEmpty(contexto)) {
                MDC.put(LogUtils.LOG_CONTEXTO, String.format("[%s]", contexto));
            }
            MDC.put(TRANSACTION_TIME_BEGIN, String.valueOf(new Date().getTime()));
        } catch (Exception e) {
            log.warn("Erro ao adicionar código de transação e contexto ao MDC", e);
        }
    }

    public static void adicionarContextoMDC(String contexto) {
        try {
            MDC.put(LogUtils.LOG_CONTEXTO, String.format("[%s]", contexto));
        } catch (Exception e) {
            log.warn("Erro ao adicionar código de transação e contexto ao MDC", e);
        }
    }

    public static void limparContextoMDC() {
        try {
            if (getCodigoTransacao() != null)
                MDC.remove(LogUtils.LOG_CD_TRANSACAO);
            if (getLogContexto() != null)
                MDC.remove(LogUtils.LOG_CONTEXTO);
        } catch (Exception e) {
            log.warn(" Erro ao limpar o MDC", e);
        }
    }

    public static String getCodigoTransacao() {
        try {
            if (MDC.get(LogUtils.LOG_CD_TRANSACAO) != null)
                return MDC.get(LOG_CD_TRANSACAO).replace("[", "").replace("]", "");
        } catch (Exception e) {
            log.warn(" Erro ao recuperar código de transação do MDC", e);
        }
        return null;
    }

    public static String getLogContexto() {
        try {
            if (MDC.get(LogUtils.LOG_CONTEXTO) != null)
                return MDC.get(LOG_CONTEXTO);
        } catch (Exception e) {
            log.warn(" Erro ao recuperar contexto do MDC", e);
        }
        return null;
    }

    public static void setTransaction(String transaction, String value) {
        try {
            MDC.put(transaction, value);
        } catch (Exception e) {
            log.warn("Erro ao adicionar {} ao MDC", transaction, e);
        }
    }

    public static String getTransaction(String transaction) {
        try {
            if (MDC.get(transaction) != null)
                return MDC.get(transaction);
        } catch (Exception e) {
            log.warn(" Erro ao recuperar {} do MDC", transaction, e);
        }
        return null;
    }
}
