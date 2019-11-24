package br.com.achimid.noverde.core.policy;

import br.com.achimid.noverde.core.policy.impl.AgePolicy;
import br.com.achimid.noverde.core.policy.impl.CommitmentPolicy;
import br.com.achimid.noverde.core.policy.impl.ScorePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PolicyFactory {

    @Autowired private AgePolicy agePolicy;
    @Autowired private ScorePolicy scorePolicy;
    @Autowired private CommitmentPolicy commitmentPolicy;

    public List<PolicyFacade> getPolicies() {
        return Arrays.asList(
                agePolicy,
                scorePolicy,
                commitmentPolicy
        );
    }

}
