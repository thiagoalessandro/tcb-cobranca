package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.BaseCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.BaseCfopResponseDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.enums.ErrorCfopEnum;
import br.com.totvs.tcb.cobranca.encryptor.pgp.PgpEncryptor;
import br.com.totvs.tcb.cobranca.exceptions.CfopException;
import br.com.totvs.tcb.cobranca.utils.LogUtils;
import br.com.totvs.tcb.cobranca.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.BaseEncoding;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Objects;

import static br.com.totvs.tcb.cobranca.utils.LogUtils.TRANSACTION_RESPONSE;

@Log4j2
public abstract class AbstractPgpEncrytor<T extends BaseCfopRequestDTO, R extends BaseCfopResponseDTO> {

    @Autowired
    private PgpEncryptor encryptor;

    protected T decrypt(String payloadEncrypted) throws CfopException {
        try {
            log.info("Payload Request Encrypted: {}", payloadEncrypted);
            String payloadDecrypted = encryptor.decrypt(payloadEncrypted);
            log.debug("Payload Request Decrypted: {}", payloadDecrypted);
            T dto = (T) parseFromJson(payloadDecrypted, getGenericRequestTypeClass());
            LogUtils.setTransaction(LogUtils.TRANSACTION_REQUESTID, dto.getRequestHeader().getRequestId());
            return dto;
        } catch (PgpEncryptor.PgpDecryptionException | IOException e) {
            throw new CfopException(e.getMessage(), ErrorCfopEnum.INVALID_DECRYPTED_REQUEST.getStatus(), e);
        }
    }

    protected String encrypt(BaseCfopResponseDTO responseDTO) throws CfopException {
        try {
            String responseJson = parseToJson(responseDTO);
            mdcResponseBase64(responseJson);
            log.debug("Payload Response Json: {}", responseJson);
            String responseEncrypted = encryptor.encrypt(responseJson);
            log.debug("Payload Response Encrypted: {}", responseEncrypted);
            return responseEncrypted;
        } catch (IOException | PgpEncryptor.PgpEncryptionException e) {
            throw new CfopException(e.getMessage(), ErrorCfopEnum.INVALID_PAYLOAD_ENCRYPTION.getStatus(), e);
        }
    }

    private void mdcResponseBase64(String response) {
        if (StringUtils.isNoneEmpty(response))
            LogUtils.setTransaction(TRANSACTION_RESPONSE, BaseEncoding.base64().encode(response.getBytes()));
    }

    protected Object parseFromJson(String payloadDecrypted, Class clazz) throws IOException {
        return new ObjectMapper().readValue(payloadDecrypted, clazz);
    }

    protected String parseToJson(Object object) throws IOException {
        return new ObjectMapper().writeValueAsString(object);
    }

    protected Class<T> getGenericRequestTypeClass() {
        try {
            String className = Objects.requireNonNull(Arrays.stream(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments())
                    .filter(type -> type.getTypeName().contains(".request")).findAny().orElse(null)).getTypeName();
            Class<?> clazz = Class.forName(className);
            return (Class<T>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    }

    protected Class<R> getGenericResponseTypeClass() {
        try {
            String className = Objects.requireNonNull(Arrays.stream(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments())
                    .filter(type -> type.getTypeName().contains(".response")).findAny().orElse(null)).getTypeName();
            Class<?> clazz = Class.forName(className);
            return (Class<R>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    }

}
