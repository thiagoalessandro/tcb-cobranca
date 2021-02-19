package br.com.totvs.tcb.cobranca.controller.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Response<T> {

    private T data;

    private List<String> errors;

    public List<String> getErrors() {
        if (errors == null)
            errors = new ArrayList<>();
        return errors;
    }

}
