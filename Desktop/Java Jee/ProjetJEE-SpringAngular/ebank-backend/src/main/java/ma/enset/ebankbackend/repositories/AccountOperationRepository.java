package ma.enset.ebankbackend.repositories;

import ma.enset.ebankbackend.entities.AccountOperation;
import ma.enset.ebankbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
