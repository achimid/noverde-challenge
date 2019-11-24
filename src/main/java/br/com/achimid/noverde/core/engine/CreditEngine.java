package br.com.achimid.noverde.core.engine;

import br.com.achimid.noverde.core.policy.PolicyFacade;
import br.com.achimid.noverde.core.policy.PolicyFactory;
import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.LoanRepository;
import br.com.achimid.noverde.loan.types.LoanStatusEnum;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreditEngine {

    @Autowired
    private LoanRepository loanRepository;

    /** Metodo de processamento das solicitações pendentes */
    public void processLoans() {
        val loans = getPendingLoans();
        loans.forEach(loan -> validatePolicies(loan));
    }

    /** Metodo que valida as politicas para cada uma das solicitações */
    private void validatePolicies(Loan loan) {

        for (PolicyFacade policy: PolicyFactory.policies) {
            policy.validatePolicy(loan);

            if (loan.isCompleted()) {
                loanRepository.save(loan);
                break;
            }
        }

        // Significa que não passou nas validações de politica
        if (loan.isCompleted()) return;
    }

    /** Retorna todas as solicitações pendente de processamento */
    private List<Loan> getPendingLoans() {
        return loanRepository.findByStatusEquals(LoanStatusEnum.PROCESSING);
    }

}