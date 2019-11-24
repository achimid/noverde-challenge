package br.com.achimid.noverde.core.policy;

import br.com.achimid.noverde.loan.Loan;

import javax.validation.constraints.NotNull;

public interface PolicyFacade {

    void validatePolicy(@NotNull Loan loan);

}
