package br.com.totvs.tcb.cobranca.controller.dto.response.cfop;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseCfopResponseDTO implements Serializable {

    private HeaderCfopResponseDTO responseHeader;

    public void generateResponseHeader() {
        this.setResponseHeader(new HeaderCfopResponseDTO());
    }
}
