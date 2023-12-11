package zada4a.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zada4a.demo.entities.Roles;




@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    Roles findByRole(String role);

}