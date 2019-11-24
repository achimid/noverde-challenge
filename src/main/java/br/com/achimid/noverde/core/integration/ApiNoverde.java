package br.com.achimid.noverde.core.integration;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class ApiNoverde {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${noverde.challenge.api.token}")
    private String apiKeyToken;

    @Value("${noverde.challenge.api.url.score}")
    private String apiKeyurlScore;

    @Value("${noverde.challenge.api.url.commitment}")
    private String apiKeyurlCommitment;


    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-api-key", apiKeyToken);
        return headers;
    }

    private Optional<Map> doPostRequest(String url, HttpEntity entity) {
        Map<String, Object> response = null;
        try {
            response = restTemplate.postForObject(url, entity, Map.class);
        }catch (Exception e) {
            log.error("Erro ao consultar api de score", e);
        }
        return Optional.of(response);
    }

    @Cacheable("score-api")
    public int consultScoreByCPF(String cpf) {
        log.info("Consultando score a API Noverde-Score");

        Map<String, String> parans = new HashMap<>();
        parans.put("cpf", cpf);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(parans, getHeaders());

        val response = doPostRequest(apiKeyurlScore, entity);

        if (!response.isPresent())
            return 0;

        return (Integer) response.get().get("score");
    }

    @Cacheable("commitment-api")
    public double consultCommitmentByCPF(String cpf) {
        log.info("Consultando score a API Noverde-Score");

        Map<String, String> parans = new HashMap<>();
        parans.put("cpf", cpf);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(parans, getHeaders());

        val response = doPostRequest(apiKeyurlCommitment, entity);

        if (!response.isPresent())
            return 0.0;

        return (Double) response.get().get("commitment");
    }


}
