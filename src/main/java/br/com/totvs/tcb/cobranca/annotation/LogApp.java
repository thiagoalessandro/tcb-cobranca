package br.com.totvs.tcb.cobranca.annotation;

import br.com.totvs.tcb.cobranca.domain.DominioRecurso;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogApp {
    DominioRecurso recurso();
}
