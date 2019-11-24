package br.com.achimid.noverde.core.policy.impl;

import br.com.achimid.noverde.core.integration.ApiNoverde;
import br.com.achimid.noverde.core.policy.PolicyFacade;
import br.com.achimid.noverde.core.util.MathUtils;
import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.types.RefusedPolicyEnum;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Component
public class CommitmentPolicy implements PolicyFacade {

    @Autowired
    private MathUtils mathUtils;



    @Autowired
    private ApiNoverde apiNoverde;

    @Override
    public void validatePolicy(@NotNull Loan loan) {

        val commitment = apiNoverde.consultCommitmentByCPF(loan.getCpf());
        loan.setCommitment(commitment);

        // Calcula o valor das parcelas segundo regras informadas
        val valorParcela = mathUtils.calcularValorParcela(loan);

        if (valorParcela.compareTo(loan.getValorSalarioNaoComprometido()) <= 0) return;

        loan.refuse(RefusedPolicyEnum.COMMITMENT);
    }

}
