package br.com.totvs.tcb.cobranca.config;

import br.com.totvs.tcb.cobranca.controller.handler.RequestInterceptorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Bean
    public RequestInterceptorHandler requestInterceptorHandler() {
        return new RequestInterceptorHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptorHandler());
    }

}
