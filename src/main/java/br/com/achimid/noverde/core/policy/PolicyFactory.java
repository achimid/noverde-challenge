package br.com.achimid.noverde.core.policy;

import br.com.achimid.noverde.core.policy.impl.AgePolicy;

import java.util.Arrays;
import java.util.List;

public class PolicyFactory {



    public static final List<PolicyFactory> getPolicies = Arrays.asList(
            new AgePolicy()
    );


}
