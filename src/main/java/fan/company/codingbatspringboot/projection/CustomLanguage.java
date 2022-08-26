package fan.company.codingbatspringboot.projection;

import fan.company.codingbatspringboot.entity.Language;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Language.class)
public interface CustomLanguage {

    Long getId();

    String getLanguage();

}
