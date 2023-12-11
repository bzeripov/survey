package zada4a.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zada4a.demo.entities.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

}
