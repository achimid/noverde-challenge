package br.com.achimid.noverde.loan;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class LoanProcess {

    @Id
    @GeneratedValue
    private Integer id;

    private String result;

    private String refusedPolicy;

    private BigDecimal amount;

    private Integer terms;

}
