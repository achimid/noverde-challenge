package br.com.achimid.noverde.api.dto;

import br.com.achimid.noverde.loan.Loan;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import java.math.BigDecimal;

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
        this.status = loan.getStatus().getValor();

        if (loan.isCompleted()) {
            val process = loan.getProcess();


            this.result = process.getResult().getValor();

            if (process.isApproved()) {
                this.amount = process.getAmount();
                this.terms = process.getTerms();
            } else {
                this.refusedPolicy = process.getRefusedPolicy();
            }
        }
    }

}
