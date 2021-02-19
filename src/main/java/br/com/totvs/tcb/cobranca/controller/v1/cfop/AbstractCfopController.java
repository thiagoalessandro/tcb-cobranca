package br.com.totvs.tcb.cobranca.controller.v1.cfop;

import br.com.totvs.tcb.cobranca.controller.dto.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;

@ApiResponses({
        @ApiResponse(code = 200, message = "Successful request"),
        @ApiResponse(code = 400, message = "Error processed")
})
public abstract class AbstractCfopController {


}
