package br.com.achimid.noverde.loan;

import br.com.achimid.noverde.loan.types.LoanResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class LoanProcess {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private LoanResultEnum result;

    private String refusedPolicy;

    private BigDecimal amount;

    private Integer terms;

    public boolean isApproved() {
        return LoanResultEnum.APPROVED.equals(result);
    }

}
