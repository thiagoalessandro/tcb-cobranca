package br.com.totvs.tcb.cobranca.exceptions;

import lombok.Getter;

import java.util.List;

public class ApiException extends Exception {

    @Getter
    private List<String> errors;

    @Getter
    private int status;

    public ApiException(Throwable e, List<String> errors, int status) {
        super(e);
        this.errors = errors;
        this.status = status;
    }

    public ApiException(Throwable e, int status) {
        super(e);
        this.status = status;
    }

}
