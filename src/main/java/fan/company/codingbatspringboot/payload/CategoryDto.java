package fan.company.codingbatspringboot.payload;

import fan.company.codingbatspringboot.entity.CategoryGlobal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotNull
    private String annatation;

    @NotNull
    private String text;

    @NotNull
    private Integer rating;

    @NotNull
    private Long categoryGlobalId;


}
