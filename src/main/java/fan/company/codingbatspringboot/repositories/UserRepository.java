package fan.company.codingbatspringboot.repositories;

import fan.company.codingbatspringboot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
