package br.com.achimid.noverde.loan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class Loan {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;
    private String cpf;
    private Date birthdate;
    private BigDecimal amount;
    private Integer terms;
    private BigDecimal income;
    private LoanStatus status;

    @OneToOne(cascade = CascadeType.PERSIST)
    private LoanProcess process;

    public boolean isCompleted() {
        return this.process != null;
    }

}
