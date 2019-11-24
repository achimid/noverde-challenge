package br.com.achimid.noverde.loan;

import br.com.achimid.noverde.api.dto.LoanErrorResponseDTO;
import br.com.achimid.noverde.api.dto.LoanRequestDTO;
import br.com.achimid.noverde.api.dto.LoanResultResponseDTO;
import br.com.achimid.noverde.api.dto.LoanSucessResponseDTO;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping("/{id}")
    public HttpEntity<?> getLoanResult(@PathVariable String id) {
        val loan = loanService.getLoan(id);
        if (!loan.isPresent())
            return ResponseEntity.status(400).body(new LoanErrorResponseDTO("id not found"));  // O enunciado solicita uma validação, mas acho que o melhor neste caso seria retornar um codigo de noContent.

        return ResponseEntity.ok(new LoanResultResponseDTO(loan.get()));
    }

    // TODO: Lembrar de modelar a resposta no formato solicitado
    @PostMapping
    public HttpEntity<LoanSucessResponseDTO> createLoan(@RequestBody @Valid LoanRequestDTO loan){
        val savedLoan = loanService.create(loan.toLoan());
        return ResponseEntity.ok(new LoanSucessResponseDTO(savedLoan));
    }
}
