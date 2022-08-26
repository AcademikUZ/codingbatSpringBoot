package fan.company.codingbatspringboot.repositories;

import fan.company.codingbatspringboot.entity.Task;
import fan.company.codingbatspringboot.projection.CustomTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "task", collectionResourceRel = "list", excerptProjection = CustomTask.class)
public interface TaskRepository extends JpaRepository<Task, Long> {
}
