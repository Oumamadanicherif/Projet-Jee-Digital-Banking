package ma.enset.ebankbackend.repositories;

import ma.enset.ebankbackend.entities.BankAccount;
import ma.enset.ebankbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
  