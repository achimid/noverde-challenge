package br.com.achimid.noverde.loan;

import br.com.achimid.noverde.core.util.DateUtils;
import br.com.achimid.noverde.loan.types.LoanResultEnum;
import br.com.achimid.noverde.loan.types.LoanStatusEnum;
import br.com.achimid.noverde.loan.types.RefusedPolicyEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
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

    public boolean isRefused() {
        return this.process != null && !this.process.isApproved();
    }

    public boolean isApproved() {
        return this.process != null && this.process.isApproved();
    }



    public int getAge() {
        val lcDate = DateUtils.getInstance().convertToLocalDateTime(this.birthdate);
        return Period.between(lcDate.toLocalDate(), LocalDate.now()).getYears();
    }

    public void refuse(RefusedPolicyEnum rfPolicy) {
        this.status = LoanStatusEnum.COMPLETED;

        this.process = new LoanProcess();
        process.setRefusedPolicy(rfPolicy);
        process.setResult(LoanResultEnum.REFUSED);
    }

    public void approve() {
        this.status = LoanStatusEnum.COMPLETED;

        this.process = new LoanProcess();
        process.setResult(LoanResultEnum.APPROVED);
    }

}
