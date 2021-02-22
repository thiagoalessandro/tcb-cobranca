package br.com.totvs.tcb.cobranca.config;

import br.com.totvs.tcb.cobranca.client.interceptor.RequestHandlerInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class TcbCobrancaConfig {

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }

    @Bean
    public RequestHandlerInterceptor requestInterceptor() {
        return new RequestHandlerInterceptor();
    }

}
