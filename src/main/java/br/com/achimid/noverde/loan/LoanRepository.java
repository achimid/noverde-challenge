package br.com.achimid.noverde.loan;

import br.com.achimid.noverde.loan.types.LoanStatusEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends CrudRepository<Loan, UUID> {

    List<Loan> findByStatusEquals(LoanStatusEnum status);

}
