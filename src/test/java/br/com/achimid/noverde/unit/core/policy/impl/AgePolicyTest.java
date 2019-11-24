package br.com.achimid.noverde.unit.core.policy.impl;

import br.com.achimid.noverde.core.policy.impl.AgePolicy;
import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.types.LoanResultEnum;
import br.com.achimid.noverde.loan.types.LoanStatusEnum;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class AgePolicyTest {

    private AgePolicy agePolicy;

    @BeforeAll
    public void before() {
        agePolicy = new AgePolicy();
        agePolicy.setAgeLimit(18);
    }

    @Test
    public void testePolicyAge_17anos_ShouldRefuse() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.YEAR, -17);

        val loan = new Loan();
        loan.setBirthdate(date.getTime());

        agePolicy.validatePolicy(loan);

        Assertions.assertTrue(loan.isCompleted());
        Assertions.assertEquals(loan.getStatus(), LoanStatusEnum.COMPLETED);
        Assertions.assertEquals(loan.getProcess().getResult(), LoanResultEnum.REFUSED);
    }

    @Test
    public void testePolicyAge_19anos_ShouldApprove() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.YEAR, -19);

        val loan = new Loan();
        loan.setBirthdate(date.getTime());

        agePolicy.validatePolicy(loan);

        Assertions.assertFalse(loan.isCompleted());
        Assertions.assertNotEquals(loan.getStatus(), LoanStatusEnum.COMPLETED);
    }

    @Test
    public void testePolicyAge_18anos_ShouldApprove() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.YEAR, -18);

        val loan = new Loan();
        loan.setBirthdate(date.getTime());

        agePolicy.validatePolicy(loan);

        Assertions.assertFalse(loan.isCompleted());
        Assertions.assertNotEquals(loan.getStatus(), LoanStatusEnum.COMPLETED);
    }

}
