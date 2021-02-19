package br.com.totvs.tcb.cobranca.client;

import br.com.totvs.tcb.cobranca.controller.dto.response.Response;
import br.com.totvs.tcb.cobranca.exceptions.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;

import java.io.IOException;

public abstract class AbstractClient {

    protected void responseHandler(FeignException e) throws ApiException {
        Response response;
        if (e.status() == 400) {
            try {
                response = new ObjectMapper().readValue(e.getMessage().split("\n")[1], Response.class);
            } catch (IOException ioException) {
                throw new ApiException(e, e.status());
            }
            throw new ApiException(e, response.getErrors(), e.status());
        }
        throw new ApiException(e, e.status());
    }

}
