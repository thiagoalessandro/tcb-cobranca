package br.com.totvs.tcb.cobranca.exceptions;

import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.enums.ErrorCfopEnum;
import lombok.Data;

@Data
public class CfopException extends Exception {

    private final String payloadEncrypted;
    private final Integer status;

    public CfopException(String message, Integer status, String payloadEncrypted) {
        super(message);
        this.payloadEncrypted = payloadEncrypted;
        this.status = status;
    }

    public CfopException(Throwable e, Integer status, String payloadEncrypted) {
        super(e);
        this.payloadEncrypted = payloadEncrypted;
        this.status = status;
    }

    public CfopException(String message, Integer status) {
        super(message);
        this.payloadEncrypted = null;
        this.status = status;
    }

    public CfopException(ErrorCfopEnum errorCfopEnum) {
        super(errorCfopEnum.name());
        this.payloadEncrypted = null;
        this.status = errorCfopEnum.getStatus();
    }

    public CfopException(String message, Integer status, Throwable e) {
        super(message, e);
        this.payloadEncrypted = null;
        this.status = status;
    }

    public CfopException(Throwable e) {
        super(e);
        this.payloadEncrypted = null;
        this.status = null;
    }

    public CfopException(Throwable e, Integer status) {
        super(e);
        this.status = status;
        this.payloadEncrypted = null;
    }

}
