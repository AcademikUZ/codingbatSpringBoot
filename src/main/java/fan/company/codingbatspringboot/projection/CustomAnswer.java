package fan.company.codingbatspringboot.projection;

import fan.company.codingbatspringboot.entity.Answer;
import fan.company.codingbatspringboot.entity.Users;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Answer.class)
public interface CustomAnswer {

    Long getId();

    String  getAnswer();

    Users getUsers();

}
