package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.controller.dto.request.cfop.BaseCfopRequestDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.cfop.BaseCfopResponseDTO;
import br.com.totvs.tcb.cobranca.model.cfop.AbstractCfopEntity;
import br.com.totvs.tcb.cobranca.utils.LogUtils;
import br.com.totvs.tcb.cobranca.utils.ModelMapperUtils;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractCfopService<R extends JpaRepository, T extends AbstractCfopEntity, E extends BaseCfopRequestDTO, S extends BaseCfopResponseDTO>
        extends AbstractCfopExecutor<E, S> {

    @Autowired
    private ModelMapperUtils modelMapperUtils;

    R repository;

    AbstractCfopService(R repository) {
        this.repository = repository;
    }

    protected abstract PropertyMap<E, T> mapRequestToEntity();

    protected abstract PropertyMap<T, S> mapEntityToResponse();

    protected T mapRequestToEntity(E requestDTO) {
        T entity = modelMapperUtils.map(requestDTO, getGenericEntityTypeClass(), mapRequestToEntity());
        loadTraceId(entity);
        entity.setCodigoUsuarioAtualizacao("DEFAULT");
        return entity;
    }

    protected S mapEntityToResponse(T entity) {
        return modelMapperUtils.map(entity, getGenericResponseTypeClass(), mapEntityToResponse());
    }

    private void loadTraceId(T entity) {
        entity.setTraceId(LogUtils.getCodigoTransacao() != null ? Long.valueOf(LogUtils.getCodigoTransacao()) : null);
    }

    protected Class<T> getGenericEntityTypeClass() {
        try {
            String className = Objects.requireNonNull(Arrays.stream(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments())
                    .filter(type -> type.getTypeName().contains(".model")).findAny().orElse(null)).getTypeName();
            Class<?> clazz = Class.forName(className);
            return (Class<T>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    }


}
