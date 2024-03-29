package br.com.achimid.noverde.api.dto;

import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.types.LoanTermsEnum;
import lombok.Data;
import lombok.val;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class LoanRequestDTO {

    /** Nome do cliente  */
    @NotEmpty(message = "name é um campo obrigatório")
    private String name;

    /** CPF do cliente  */
    @NotEmpty(message = "cpf é um campo obrigatório")
    private String cpf;

    /** Data de nascimento do cliente  */
    @DateTimeFormat(style = "yyyy-MM-dd")
    @NotNull(message = "birthdate é um campo obrigatório")
    private Date birthdate;

    /** Valor desejado, entre R$ 1.000,00 e R$ 4.000,00  */
    @NotNull(message = "amount é um campo obrigatório")
    @DecimalMin(value = "1000.00", message = "O valor deve ser entre R$ 1.000,00 e R$ 4.000,00")
    @DecimalMax(value = "4000.00", message = "O valor deve ser entre R$ 1.000,00 e R$ 4.000,00")
    private BigDecimal amount;

    /** Quantidade de parcelas desejadas. Valores disponíveis: 6, 9 ou 12  */
    @NotNull(message = "terms é um campo obrigatório")
    @Pattern(regexp = "6|9|12", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Valores válidos para terms (6,9,12)")
    private String terms;

    /** Renda mensal do cliente  */
    @NotNull(message = "income é um campo obrigatório")
    private BigDecimal income;


    public Loan toLoan() {
        val loan = new Loan();

        loan.setName(this.name);
        loan.setAmount(this.amount);
        loan.setBirthdate(this.birthdate);
        loan.setCpf(this.cpf);
        loan.setIncome(this.income);
        loan.setTerms(LoanTermsEnum.valueOf("X" + terms));

        return loan;
    }


}
