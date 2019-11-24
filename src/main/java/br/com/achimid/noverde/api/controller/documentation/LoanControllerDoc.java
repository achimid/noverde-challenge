package br.com.achimid.noverde.api.controller.documentation;

import br.com.achimid.noverde.api.dto.LoanRequestDTO;
import br.com.achimid.noverde.api.dto.LoanSucessResponseDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface LoanControllerDoc {

    @ApiOperation("Consultar se a solicitação já foi processada e obter o resultado")
    HttpEntity<?> getLoanResult(@PathVariable String id);

    @ApiOperation("Criar uma nova solicitação")
    HttpEntity<LoanSucessResponseDTO> createLoan(@RequestBody @Valid LoanRequestDTO loan);
}
