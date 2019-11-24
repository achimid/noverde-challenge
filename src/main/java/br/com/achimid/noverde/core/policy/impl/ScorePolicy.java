package br.com.achimid.noverde.core.policy.impl;

import br.com.achimid.noverde.core.integration.ApiNoverde;
import br.com.achimid.noverde.core.policy.PolicyFacade;
import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.types.RefusedPolicyEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Slf4j
@Component
public class ScorePolicy implements PolicyFacade {

    @Setter
    @Value("${policy.scoreLimit}")
    private int scoreLimit;

    @Autowired
    private ApiNoverde apiNoverde;


    @Override
    public void validatePolicy(@NotNull Loan loan) {
        log.info("Validando politica de Score");

        int scoreConsulted = apiNoverde.consultScoreByCPF(loan.getCpf());
        loan.setScore(scoreConsulted);

        if (scoreLimit < scoreConsulted) return;

        loan.refuse(RefusedPolicyEnum.SCORE);
    }


}
