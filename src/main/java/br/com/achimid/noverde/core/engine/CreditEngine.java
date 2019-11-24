package br.com.achimid.noverde.core.engine;

import br.com.achimid.noverde.core.policy.PolicyFacade;
import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.LoanRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreditEngine {

    @Autowired
    private LoanRepository loanRepository;

    public void processLoans() {
        val loans = getPendingLoans();

    }

    private ArrayList<Loan> getPendingLoans() {
        return null;
    }


}
