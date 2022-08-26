package fan.company.codingbatspringboot.projection;

import fan.company.codingbatspringboot.entity.Answer;
import fan.company.codingbatspringboot.entity.Task;
import fan.company.codingbatspringboot.entity.Users;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Task.class)
public interface CustomTask {

    Long getId();

    String  getTask();


}
