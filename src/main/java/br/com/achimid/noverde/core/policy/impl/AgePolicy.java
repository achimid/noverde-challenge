package br.com.achimid.noverde.core.policy.impl;

import br.com.achimid.noverde.core.policy.PolicyFacade;
import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.LoanRepository;
import br.com.achimid.noverde.loan.types.RefusedPolicyEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Slf4j
@Component
public class AgePolicy implements PolicyFacade {

    @Autowired
    private LoanRepository loanRepository;

    @Setter
    @Value("${policy.ageLimit}")
    private int ageLimit;

    @Override
    public void validatePolicy(@NotNull Loan loan) {
        log.info("Validando politica de Idade");

        if (loan.getAge() >= ageLimit) return;

        loan.refuse(RefusedPolicyEnum.AGE);
    }

}
