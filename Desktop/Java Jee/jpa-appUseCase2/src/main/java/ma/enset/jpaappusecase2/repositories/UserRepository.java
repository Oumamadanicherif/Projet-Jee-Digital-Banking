package ma.enset.jpaappusecase2.repositories;

import ma.enset.jpaappusecase2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>  {
User findByUsername(String userName);
}
