package fan.company.codingbatspringboot.repositories;

import fan.company.codingbatspringboot.entity.Answer;
import fan.company.codingbatspringboot.projection.CustomAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "answer", collectionResourceRel = "list", excerptProjection = CustomAnswer.class)
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
