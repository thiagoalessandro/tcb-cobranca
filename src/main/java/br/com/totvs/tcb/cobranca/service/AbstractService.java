package br.com.totvs.tcb.cobranca.service;

import br.com.totvs.tcb.cobranca.config.Messages;
import br.com.totvs.tcb.cobranca.model.AbstractEntity;
import br.com.totvs.tcb.cobranca.repository.GenericRepository;
import br.com.totvs.tcb.cobranca.utils.ModelMapperUtils;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

public abstract class AbstractService<R extends GenericRepository, T extends AbstractEntity, ID> extends GenericService<R, T, ID> {

    @Autowired
    private ModelMapperUtils modelMapperUtils;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    Messages messages;

    public AbstractService(R repository) {
        super(repository);
    }

    public List<?> convertToListDTO(List<? extends T> list, Class clazz) {
        return modelMapperUtils.mapAll(list, clazz, null);
    }

    public List<?> convertToListDTO(List<? extends T> list, Class clazz, PropertyMap... propertyMap) {
        return modelMapperUtils.mapAll(list, clazz, propertyMap);
    }

    public Object convertToSingleDTO(T entity, Class clazz) {
        return modelMapperUtils.map(entity, clazz, null);
    }

    public Object convertToSingleDTO(T entity, Class clazz, PropertyMap... propertyMap) {
        return modelMapperUtils.map(entity, clazz, propertyMap);
    }

    public Object mapSingleObjectGeneric(Object source, Class clazz) {
        return modelMapperUtils.map(source, clazz, null);
    }

    public Object mapSingleObjectGeneric(Object source, Class clazz, PropertyMap... propertyMap) {
        return modelMapperUtils.map(source, clazz, propertyMap);
    }

    public List<?> mapAllObjectGeneric(List<?> list, Class clazz) {
        return modelMapperUtils.mapAll(list, clazz, null);
    }

    public List<?> mapAllObjectGeneric(List<?> list, Class clazz, PropertyMap... propertyMap) {
        return modelMapperUtils.mapAll(list, clazz, propertyMap);
    }

}
