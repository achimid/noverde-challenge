package br.com.achimid.noverde.core.policy.impl;

import br.com.achimid.noverde.core.policy.PolicyFacade;
import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.types.RefusedPolicyEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ScorePolicy implements PolicyFacade {


    @Value("${noverde.challenge.api.token}")
    private String apiKeyToken;

    @Value("${noverde.challenge.api.url}")
    private String apiKeyurl;

    @Setter
    @Value("${policy.scoreLimit}")
    private int scoreLimit;


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void validatePolicy(@NotNull Loan loan) {
        log.info("Validando politica de Score");

        int scoreConsulted = this.consultScore(loan.getCpf());
        if (scoreLimit < scoreConsulted) return;

        loan.refuse(RefusedPolicyEnum.SCORE);
    }

    private int consultScore(String cpf) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-api-key", apiKeyToken);

        Map<String, String> parans = new HashMap<>();
        parans.put("cpf", cpf);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(parans, headers);

        Map<String, Integer> response = null;
        try {
            response = restTemplate.postForObject(apiKeyurl, request, Map.class);
        }catch (Exception e) {
            log.error("Erro ao consultar api de score", e);
            return 0;
        }

        return response.get("score").intValue();
    }

}
