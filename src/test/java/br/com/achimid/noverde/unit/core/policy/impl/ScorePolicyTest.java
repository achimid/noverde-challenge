package br.com.achimid.noverde.unit.core.policy.impl;

import br.com.achimid.noverde.ApplicationConfig;
import br.com.achimid.noverde.core.policy.impl.ScorePolicy;
import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.types.LoanResultEnum;
import br.com.achimid.noverde.loan.types.LoanStatusEnum;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class ScorePolicyTest {

    @Autowired
    ScorePolicy scorePolicy;

    @BeforeEach
    public void before() {
        scorePolicy.setScoreLimit(600);
    }

    @Test
    public void testePolicyScore_ShouldRefuse() {
        val loan = new Loan();
        loan.setCpf("41422297899");

        scorePolicy.validatePolicy(loan);

        Assertions.assertTrue(loan.isCompleted());
        Assertions.assertEquals(loan.getStatus(), LoanStatusEnum.COMPLETED);
        Assertions.assertEquals(loan.getProcess().getResult(), LoanResultEnum.REFUSED);
    }

    @Test
    public void testePolicyScore_ShouldApprove() {
        val loan = new Loan();
        loan.setCpf("654157987915");

        scorePolicy.validatePolicy(loan);

        Assertions.assertFalse(loan.isCompleted());
        Assertions.assertEquals(loan.getStatus(), LoanStatusEnum.PROCESSING);
    }
}
