package br.com.achimid.noverde.unit.core.policy;

import br.com.achimid.noverde.core.policy.PolicyFactory;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PolicyFactoryTest {

    @Autowired
    private PolicyFactory policyFactory;

    @Test
    public void testeFactoryInstance() {
        val policies = policyFactory.getPolicies();

        Assertions.assertNotNull(policies);
        Assertions.assertEquals(policies.size(), 3);
    }

}
