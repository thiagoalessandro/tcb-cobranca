package br.com.totvs.tcb.cobranca.controller.dto.response.cfop.enums;

public enum ErrorCfopEnum {

    INVALID_API_VERSION(400),
    INVALID_PAYLOAD_SIGNATURE(401),
    INVALID_PAYLOAD_ENCRYPTION(400),
    REQUEST_TIMESTAMP_OUT_OF_RANGE(400),
    INVALID_IDENTIFIER(404),
    IDEMPOTENCY_VIOLATION(412),
    INVALID_FIELD_VALUE(400),
    MISSING_REQUIRED_FIELD(400),
    PRECONDITION_VIOLATION(400),
    USER_ACTION_IN_PROGRESS(400),
    INVALID_DECRYPTED_REQUEST(400);

    private final int status;

    ErrorCfopEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
