package br.com.achimid.noverde.api.dto;

import br.com.achimid.noverde.loan.Loan;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoanSucessResponseDTO {

    private String id;

    public LoanSucessResponseDTO(@NonNull Loan loan) {
        this.id = loan.getId().toString();
    }

}
