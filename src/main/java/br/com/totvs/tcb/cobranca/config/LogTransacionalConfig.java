package br.com.totvs.tcb.cobranca.config;

import br.com.totvs.tcb.cobranca.annotation.LogApp;
import br.com.totvs.tcb.cobranca.exceptions.CfopException;
import br.com.totvs.tcb.cobranca.exceptions.ValidationException;
import br.com.totvs.tcb.cobranca.utils.LogUtils;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Aspect
@Configuration
public class LogTransacionalConfig {

    @Pointcut("@annotation(logApp)")
    public void callAt(LogApp logApp) {
    }

    @Around("callAt(logApp)")
    public Object around(ProceedingJoinPoint pjp,
                         LogApp logApp) throws Throwable {
        LogUtils.setTransaction(LogUtils.TRANSACTION_RESOURCE, logApp.recurso().name());
        return pjp.proceed();
    }

}
