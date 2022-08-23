package fan.company.codingbatspringboot.repositories;

import fan.company.codingbatspringboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
