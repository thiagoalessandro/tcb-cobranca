package br.com.totvs.tcb.cobranca.controller.handler;

import br.com.totvs.tcb.cobranca.exceptions.CfopException;
import br.com.totvs.tcb.cobranca.utils.LogUtils;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CfopException.class)
    public ResponseEntity cfopException(CfopException e) {
        registerException(e);
        return ResponseEntity.status(e.getStatus()).body(e.getPayloadEncrypted());
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity retryableException(RetryableException e) {
        registerException(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private void registerException(Exception e) {
        log.error(e);
        try {
            LogUtils.setTransaction(LogUtils.TRANSACTION_EXCEPTION, ExceptionUtils.getStackTrace(e.getCause()));
        } catch (Exception e2) {
            log.error(e2);
        }
    }

}
