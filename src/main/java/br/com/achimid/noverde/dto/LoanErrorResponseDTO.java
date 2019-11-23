package br.com.achimid.noverde.dto;

import br.com.achimid.noverde.loan.Loan;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
public class LoanErrorResponseDTO {

    private ArrayList errors;


}
