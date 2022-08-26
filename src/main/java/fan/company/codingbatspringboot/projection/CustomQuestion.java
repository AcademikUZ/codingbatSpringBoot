package fan.company.codingbatspringboot.projection;

import fan.company.codingbatspringboot.entity.Answer;
import fan.company.codingbatspringboot.entity.Question;
import fan.company.codingbatspringboot.entity.Users;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Question.class)
public interface CustomQuestion {

    Long getId();

    String getQuestion();

    Users getUsers();

}
