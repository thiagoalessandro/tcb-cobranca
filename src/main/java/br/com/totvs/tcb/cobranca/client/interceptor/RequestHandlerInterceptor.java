package br.com.totvs.tcb.cobranca.client.interceptor;

import br.com.totvs.tcb.cobranca.utils.LogUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class RequestHandlerInterceptor implements RequestInterceptor {

    @Value("${tcb.processador.apiKey}")
    private String apiKeyProcessador;

    @Override
    public void apply(RequestTemplate template) {
        template.header("X-TRANSACTION", LogUtils.getCodigoTransacao());
        template.header("X-AUTHORIZATION", apiKeyProcessador);
    }

}
