package br.com.achimid.noverde.core.jobs;

import br.com.achimid.noverde.core.engine.CreditEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class CreditEvaluationTask {

    @Autowired
    private CreditEngine creditEngine;

    @Scheduled(fixedDelayString = "${process.delay}")
    public void run() {
        try {
            creditEngine.processLoans();
        } catch (Exception e) {
            log.error("Erro ao processar o job de solicitações", e);
        }
    }
}
