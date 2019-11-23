package br.com.achimid.noverde.dto;

import br.com.achimid.noverde.loan.Loan;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class LoanResultResponseDTO {

    /** UUID requisitado */
    private String id;

    /** Status atual da solicitação. Possíveis valores: processing, completed */
    private String status;

    /** Em caso de status completed, os valores podem ser approved ou refused, caso contrário será null */
    private String result;

    /** Em caso de result refused, os valores podem ser: age se foi recusado na política de idade, score na política de score ou commitment na política de comprometimento. Caso não tenha sido negado, será null. */
    private String refusedPolicy;

    /** Montante liberado em caso de proposta aprovada. Caso contrário deve ser null */
    private BigDecimal amount;

    /** Quantidade de parcelas aprovadas na oferta. Caso a proposta tenha sido recusada, deve ser null */
    private Integer terms;

    public LoanResultResponseDTO(@NonNull Loan loan) {
        this.id = loan.getId().toString();

        if (loan.isCompleted()) {
            this.status = loan.getStatus().toString();
            this.result = loan.getProcess().getResult();
            this.refusedPolicy = loan.getProcess().getRefusedPolicy();
            this.amount = loan.getProcess().getAmount();
            this.terms = loan.getProcess().getTerms();
        }
    }

}
