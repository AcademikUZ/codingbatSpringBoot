package fan.company.codingbatspringboot.repositories;

import fan.company.codingbatspringboot.entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctionRepository extends JpaRepository<Function, Long> {
}
