package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.BaseCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.BaseCfopResponseDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.ErrorCfopResponseDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.enums.ErrorCfopEnum;
import br.com.totvs.tcb.cobranca.exceptions.CfopException;
import br.com.totvs.tcb.cobranca.model.LogTransacional;
import br.com.totvs.tcb.cobranca.service.LogTransacionalService;
import br.com.totvs.tcb.cobranca.utils.StringUtils;
import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractCfopExecutor<T extends BaseCfopRequestDTO, R extends BaseCfopResponseDTO> extends AbstractPgpEncrytor<T, R> {

    @Autowired
    private LogTransacionalService logTransacionalService;

    protected abstract R processor(T requestDTO) throws CfopException;

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = CfopException.class)
    public String executor(String payloadEncrypted) throws CfopException {
        R responseDTO;
        try {
            T requestDTO = decrypt(payloadEncrypted);
            String responsePayloadEncrypted = validateRequestDuplicated(requestDTO);
            if (StringUtils.isNoneEmpty(responsePayloadEncrypted))
                return responsePayloadEncrypted;
            responseDTO = processor(requestDTO);
            responseDTO.generateResponseHeader();
            return encrypt(responseDTO);
        } catch (CfopException e) {
            Optional<ErrorCfopEnum> optionalErrorCfopEnum = Arrays.stream(ErrorCfopEnum.values())
                    .filter(errorCfopEnum -> errorCfopEnum.name().equals(e.getMessage()))
                    .findFirst();
            if (Objects.isNull(e.getPayloadEncrypted()) && optionalErrorCfopEnum.isPresent()) {
                ErrorCfopEnum errorCfopEnum = optionalErrorCfopEnum.get();
                throw new CfopException(errorCfopEnum.name(), errorCfopEnum.getStatus(), generateEncryptedErrorResponse(errorCfopEnum));
            } else {
                throw new CfopException(e, e.getStatus(), e.getPayloadEncrypted());
            }

        }
    }

    private String generateEncryptedErrorResponse(ErrorCfopEnum errorCfopEnum) throws CfopException {
        ErrorCfopResponseDTO errorCfopResponseDTO = new ErrorCfopResponseDTO();
        errorCfopResponseDTO.generateResponseHeader();
        errorCfopResponseDTO.setErrorResponseCode(errorCfopEnum.getStatus());
        errorCfopResponseDTO.setErrorDescription(errorCfopEnum.name());
        return encrypt(errorCfopResponseDTO);
    }

    private String validateRequestDuplicated(T requestDTO) throws CfopException {
        try {
            Optional<LogTransacional> optionalLogTransacional = logTransacionalService.findByFirstRequestId(requestDTO.getRequestHeader().getRequestId());
            if (optionalLogTransacional.isPresent()) {
                LogTransacional logTransacional = optionalLogTransacional.get();
                if (logTransacional.getResponseStatus() == 200) {
                    R responseDeliveredDTO = (R) parseFromJson(new String(BaseEncoding.base64().decode(logTransacional.getResponse()), Charsets.UTF_8), getGenericResponseTypeClass());
                    responseDeliveredDTO.getResponseHeader().setResponseTimestamp(new Date().getTime());
                    return encrypt(responseDeliveredDTO);
                }
            }
        } catch (Exception e) {
            throw new CfopException(e, HttpStatus.BAD_REQUEST.value());
        }
        return null;
    }

}
