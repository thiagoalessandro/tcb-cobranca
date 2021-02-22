package br.com.totvs.tcb.cobranca.service;

import br.com.totvs.tcb.cobranca.controller.dto.response.LogTransacionalResponseDTO;
import br.com.totvs.tcb.cobranca.exceptions.ValidationException;
import br.com.totvs.tcb.cobranca.model.LogTransacional;
import br.com.totvs.tcb.cobranca.repository.LogTransacionalRepository;
import br.com.totvs.tcb.cobranca.repository.specification.CustomRsqlVisitor;
import br.com.totvs.tcb.cobranca.utils.ModelMapperUtils;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.font.OpenType;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class LogTransacionalService {

    @Autowired
    private LogTransacionalRepository repository;

    @Autowired
    private ModelMapperUtils modelMapperUtils;

    @Async
    @EventListener(LogTransacional.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public void onCreate(LogTransacional logTransacional) {
        log.info("Registrando log transacional...");
        log.debug("LogTransacional {}...", logTransacional.toString());
        repository.save(logTransacional);
    }

    public Optional<LogTransacional> findByLastRequestId(String requestId) {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "dataHoraInicio"));
        List<LogTransacional> logTransacionais = repository.findByRequestId(requestId, pageable).getContent();
        if (logTransacionais.size() > 0)
            return Optional.of(logTransacionais.get(0));
        return Optional.empty();
    }

    public Page<LogTransacionalResponseDTO> search(String search, Integer page, Integer size) throws ValidationException {
        try {
            Node rootNode = new RSQLParser().parse(search);
            Specification<LogTransacional> spec = rootNode.accept(new CustomRsqlVisitor<>());
            Pageable pageable = PageRequest.of(page, size, Sort.by("dataHoraInicio").descending());
            Page<LogTransacional> logTransacionalPage = repository.findAll(spec, pageable);
            return logTransacionalPage.map(logTransacional -> modelMapperUtils.map(logTransacional, LogTransacionalResponseDTO.class));
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

}
