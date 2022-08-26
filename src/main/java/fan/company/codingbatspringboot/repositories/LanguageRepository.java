package fan.company.codingbatspringboot.repositories;

import fan.company.codingbatspringboot.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
