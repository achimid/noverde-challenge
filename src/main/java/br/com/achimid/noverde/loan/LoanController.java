package br.com.achimid.noverde.loan;

import br.com.achimid.noverde.dto.LoanRequestDTO;
import br.com.achimid.noverde.dto.LoanResultResponseDTO;
import br.com.achimid.noverde.dto.LoanSucessResponseDTO;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping("/{id}")
    public HttpEntity<?> getLoanResult(@PathVariable String id) {
        val loan = loanService.getLoan(id);
        return ResponseEntity.ok(new LoanResultResponseDTO(loan));
    }

    @PostMapping
    public HttpEntity<LoanSucessResponseDTO> createLoan(@RequestBody LoanRequestDTO loan){
        val savedLoan = loanService.create(loan.toLoan());
        return ResponseEntity.ok(new LoanSucessResponseDTO(savedLoan));
    }
}
