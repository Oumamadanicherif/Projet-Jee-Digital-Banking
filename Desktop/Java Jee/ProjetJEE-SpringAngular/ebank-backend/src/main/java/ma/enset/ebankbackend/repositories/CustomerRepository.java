package ma.enset.ebankbackend.repositories;

import ma.enset.ebankbackend.entities.AccountOperation;
import ma.enset.ebankbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select c from Customer c where c.name like :kw")
    List<Customer> searchCustomer(@Param("kw") String keyword);
    //List<Customer> findByNameContains(String keyword);//DTO
}
