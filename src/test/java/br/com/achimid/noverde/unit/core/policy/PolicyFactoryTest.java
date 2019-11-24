package br.com.achimid.noverde.unit.core.policy;

import br.com.achimid.noverde.core.policy.PolicyFactory;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PolicyFactoryTest {

    @Test
    public void testeFactoryInstance() {
        val policies = PolicyFactory.policies;

        Assert.assertNotNull(policies);
        Assert.assertEquals(policies.size(), 3);
    }

}
