package br.com.totvs.tcb.cobranca.controller.v1.cfop;


import br.com.totvs.tcb.cobranca.annotation.LogApp;
import br.com.totvs.tcb.cobranca.controller.resource.ResourceCfopWSRest;
import br.com.totvs.tcb.cobranca.domain.DominioRecurso;
import br.com.totvs.tcb.cobranca.exceptions.CfopException;
import br.com.totvs.tcb.cobranca.service.cfop.ConectividadeCfopService;
import br.com.totvs.tcb.cobranca.service.cfop.CobrancaCfopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "Google CFOP", tags = {"Google CFOP"})
@RestController
@RequestMapping(ResourceCfopWSRest.CFOP_V1)
public class GoogleCfopController extends AbstractCfopController {

    private final ConectividadeCfopService conectividadeCfopService;
    private final CobrancaCfopService cobrancaCfopService;

    public GoogleCfopController(ConectividadeCfopService conectividadeCfopService,
                                CobrancaCfopService cobrancaCfopService) {
        this.conectividadeCfopService = conectividadeCfopService;
        this.cobrancaCfopService = cobrancaCfopService;
    }

    @PostMapping("/connectivityTest")
    @ApiOperation(value = "Connectivity test")
    @LogApp(recurso = DominioRecurso.CONECTIVIDADE)
    public ResponseEntity<String> connectivity(@RequestBody String payload) throws CfopException {
        return ResponseEntity.ok(conectividadeCfopService.executor(payload));
    }

    @PostMapping("/generateReferenceNumber")
    @ApiOperation(value = "Generate Reference Number")
    @LogApp(recurso = DominioRecurso.SOLICITACAO)
    public ResponseEntity<String> generateReferenceNumber(@RequestBody String payload) throws CfopException {
        return ResponseEntity.ok(cobrancaCfopService.executor(payload));
    }

}

