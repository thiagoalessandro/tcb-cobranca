package br.com.totvs.tcb.cobranca.config;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Messages {

    private final ResourceBundleMessageSource messageSource;

    public Messages(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String code) {
        return messageSource.getMessage(code, new Object[]{}, getLocale());
    }

    public String get(String code, Object[] args) {
        return messageSource.getMessage(code, args, getLocale());
    }

    public String get(String code, String arg) {
        return messageSource.getMessage(code, new Object[]{arg}, getLocale());
    }

    public Locale getLocale() {
        return Locale.ENGLISH;
    }

}
