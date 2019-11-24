package br.com.achimid.noverde.unit.core.policy.impl;

import br.com.achimid.noverde.core.policy.impl.CommitmentPolicy;
import br.com.achimid.noverde.core.policy.impl.ScorePolicy;
import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.types.LoanResultEnum;
import br.com.achimid.noverde.loan.types.LoanStatusEnum;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CommitmentPolicyTest {

    @Autowired
    CommitmentPolicy commitmentPolicy;

    @Test
    public void testePolicyCommitment_ShouldRefuse() {
        val loan = new Loan();
        loan.setCpf("41422297899");
        loan.setScore(900);
        loan.setAmount(new BigDecimal(5000));
        loan.setIncome(new BigDecimal(5000));

        commitmentPolicy.validatePolicy(loan);

        Assertions.assertTrue(loan.isCompleted());
        Assertions.assertEquals(loan.getStatus(), LoanStatusEnum.COMPLETED);
        Assertions.assertEquals(loan.getProcess().getResult(), LoanResultEnum.REFUSED);
    }

    @Test
    public void testePolicyCommitment_ShouldRefuseWithLowScore() {
        val loan = new Loan();
        loan.setCpf("41422297899");
        loan.setScore(700);
        loan.setAmount(new BigDecimal(5000));
        loan.setIncome(new BigDecimal(5000));

        commitmentPolicy.validatePolicy(loan);

        Assertions.assertTrue(loan.isCompleted());
        Assertions.assertEquals(loan.getStatus(), LoanStatusEnum.COMPLETED);
        Assertions.assertEquals(loan.getProcess().getResult(), LoanResultEnum.REFUSED);
    }

    @Test
    public void testePolicyCommitment_ShouldRefuseWithHighAmount() {
        val loan = new Loan();
        loan.setCpf("41422297899");
        loan.setScore(700);
        loan.setAmount(new BigDecimal(5000));
        loan.setIncome(new BigDecimal(1000));

        commitmentPolicy.validatePolicy(loan);

        Assertions.assertTrue(loan.isCompleted());
        Assertions.assertEquals(loan.getStatus(), LoanStatusEnum.COMPLETED);
        Assertions.assertEquals(loan.getProcess().getResult(), LoanResultEnum.REFUSED);
    }

    @Test
    public void testePolicyCommitment_ShouldApprove() {
        val loan = new Loan();
        loan.setCpf("41422296666");
        loan.setScore(900);
        loan.setAmount(new BigDecimal(5000));
        loan.setIncome(new BigDecimal(5000));

        commitmentPolicy.validatePolicy(loan);

        Assertions.assertTrue(loan.isCompleted());
        Assertions.assertEquals(loan.getStatus(), LoanStatusEnum.COMPLETED);
        Assertions.assertEquals(loan.getProcess().getResult(), LoanResultEnum.APPROVED);
    }
}
