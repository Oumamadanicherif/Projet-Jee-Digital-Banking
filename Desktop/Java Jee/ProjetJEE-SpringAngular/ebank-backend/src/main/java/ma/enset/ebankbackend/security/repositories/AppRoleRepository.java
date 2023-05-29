package  ma.enset.ebankbackend.security.repositories;
import ma.enset.ebankbackend.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findAppRoleByRoleName(String username);
}
