package ma.enset.jpaappusecase2.service;

import ma.enset.jpaappusecase2.entities.Role;
import ma.enset.jpaappusecase2.entities.User;

public interface UserService  {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String roleName );
   User authenticate(String userName, String password);
}
