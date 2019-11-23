package br.com.achimid.noverde.loan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoanRepository extends CrudRepository<Loan, UUID> {
}
