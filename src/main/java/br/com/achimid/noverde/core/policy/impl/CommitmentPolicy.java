package br.com.achimid.noverde.core.policy.impl;

import br.com.achimid.noverde.core.policy.PolicyFacade;
import br.com.achimid.noverde.loan.Loan;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class CommitmentPolicy implements PolicyFacade {

    @Override
    public void validatePolicy(@NotNull Loan loan) {

    }

}
