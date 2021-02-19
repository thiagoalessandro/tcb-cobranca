package br.com.totvs.tcb.cobranca.controller.v1;

import br.com.totvs.tcb.cobranca.annotation.LogApp;
import br.com.totvs.tcb.cobranca.controller.dto.response.LogTransacionalResponseDTO;
import br.com.totvs.tcb.cobranca.controller.dto.response.Response;
import br.com.totvs.tcb.cobranca.controller.resource.ResourceDefaultWSRest;
import br.com.totvs.tcb.cobranca.domain.DominioRecurso;
import br.com.totvs.tcb.cobranca.exceptions.ValidationException;
import br.com.totvs.tcb.cobranca.service.LogTransacionalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Logs Transacionais", tags = "Logs Transacionais")
@RestController
@RequestMapping(ResourceDefaultWSRest.LOG_TRANSACIONAL)
public class LogTransacionalController {

    private final LogTransacionalService logTransacionalService;

    public LogTransacionalController(LogTransacionalService logTransacionalService) {
        this.logTransacionalService = logTransacionalService;
    }

    @GetMapping
    @LogApp(recurso = DominioRecurso.LOG_TRANSACIONAL)
    @ApiOperation(value = "Consultar logs transacionais")
    public ResponseEntity<Response<Page<LogTransacionalResponseDTO>>> search(@RequestParam(value = "search") String search,
                                                                             @RequestParam(name = "page") Integer page,
                                                                             @RequestParam(name = "size") Integer size) throws ValidationException {
        Response<Page<LogTransacionalResponseDTO>> response = new Response<>();
        Page<LogTransacionalResponseDTO> logTransacionalPage;
        logTransacionalPage = logTransacionalService.search(search, page, size);
        response.setData(logTransacionalPage);
        return ResponseEntity.ok(response);
    }

}
