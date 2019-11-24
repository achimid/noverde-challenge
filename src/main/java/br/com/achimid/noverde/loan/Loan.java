package br.com.achimid.noverde.loan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class Loan {

    @Id
    private UUID id = UUID.randomUUID();

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @NotNull
    private Date birthdate;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Integer terms;

    @NotNull
    private BigDecimal income;

    @Enumerated(value = EnumType.STRING)
    private LoanStatusEnum status = LoanStatusEnum.PROCESSING;

    @OneToOne(cascade = CascadeType.PERSIST)
    private LoanProcess process;

    public boolean isCompleted() {
        return this.process != null;
    }

}
