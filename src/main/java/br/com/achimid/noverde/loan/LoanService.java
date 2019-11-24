package br.com.achimid.noverde.loan;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public Loan create(@NonNull Loan loan) {
        return loanRepository.save(loan);
    }

    public Optional<Loan> getLoan(@NonNull String id) {
        return loanRepository.findById(UUID.fromString(id));
    }

}
