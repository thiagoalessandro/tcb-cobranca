package br.com.totvs.tcb.cobranca.service.cfop;

import br.com.totvs.tcb.cobranca.model.cfop.ContaIntegradoraCfop;
import br.com.totvs.tcb.cobranca.repository.ContaIntegradoraCfopRepository;
import br.com.totvs.tcb.cobranca.service.AbstractService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class ContaIntegradoraCfopService extends AbstractService<ContaIntegradoraCfopRepository, ContaIntegradoraCfop, Long> {

    ContaIntegradoraCfopService(ContaIntegradoraCfopRepository repository) {
        super(repository);
    }

    public Optional<ContaIntegradoraCfop> findByContaIntegradora(String contaIntegradora) {
        return repository.findByContaIntegradora(contaIntegradora);
    }

}
