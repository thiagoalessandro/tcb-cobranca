package br.com.totvs.tcb.cobranca.service;

import br.com.totvs.tcb.cobranca.domain.DominioSituacaoRegistro;
import br.com.totvs.tcb.cobranca.model.AbstractEntity;
import br.com.totvs.tcb.cobranca.repository.GenericRepository;
import br.com.totvs.tcb.cobranca.utils.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
public abstract class GenericService<R extends GenericRepository, T extends AbstractEntity, ID> {

    protected R repository;

    GenericService(R repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return (T) repository.save(entity);
    }

    public T saveAndFlush(T entity) {
        return (T) repository.saveAndFlush(entity);
    }

    public Iterable<? extends T> saveAll(Iterable<? extends T> entities) {
        return repository.saveAll(entities);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public void deleteLogical(T entity) {
        entity.setSituacao(DominioSituacaoRegistro.EXCLUIDO);
        repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<T> findByAtivo() {
        return repository.findBySituacao(DominioSituacaoRegistro.ATIVO);
    }

    public Pageable generatePageable(Integer page, Integer size) {
        if (page == null || size == null)
            return null;
        return PageRequest.of(page, size);
    }

    public Pageable generatePageable(Map<String, String> params) {
        if (StringUtils.noNull(params.get("page")).isEmpty() || StringUtils.noNull(params.get("count")).isEmpty())
            return null;
        return PageRequest.of(Integer.parseInt(params.get("page")), Integer.parseInt(params.get("count")));
    }


}
