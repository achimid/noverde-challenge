package br.com.achimid.noverde.core.policy;

import br.com.achimid.noverde.core.policy.impl.AgePolicy;
import br.com.achimid.noverde.core.policy.impl.CommitmentPolicy;
import br.com.achimid.noverde.core.policy.impl.ScorePolicy;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolicyFactory {

    public static final List<PolicyFacade> policies = Arrays.asList(
            new AgePolicy(),
            new ScorePolicy(),
            new CommitmentPolicy()
    );

}
