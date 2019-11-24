package br.com.achimid.noverde.core.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ApiScore {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${noverde.challenge.api.token}")
    private String apiKeyToken;

    @Value("${noverde.challenge.api.url}")
    private String apiKeyurl;



    public int consultScoreByCPF(String cpf) {
        log.info("Consultando score a API Noverde-Score");

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
