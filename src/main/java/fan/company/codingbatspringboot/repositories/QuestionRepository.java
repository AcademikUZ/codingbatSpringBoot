package fan.company.codingbatspringboot.repositories;


import fan.company.codingbatspringboot.entity.Question;
import fan.company.codingbatspringboot.projection.CustomQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "question", collectionResourceRel = "list", excerptProjection = CustomQuestion.class)
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
