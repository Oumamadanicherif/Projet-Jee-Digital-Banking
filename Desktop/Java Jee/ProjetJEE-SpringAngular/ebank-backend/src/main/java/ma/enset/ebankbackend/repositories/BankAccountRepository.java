package ma.enset.ebankbackend.repositories;
import ma.enset.ebankbackend.entities.BankAccount;
import ma.enset.ebankbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    List<BankAccount> findByCustomer(Customer customer);
}
  