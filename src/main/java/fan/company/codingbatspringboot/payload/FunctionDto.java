package fan.company.codingbatspringboot.payload;

import fan.company.codingbatspringboot.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FunctionDto {

    @NotNull
    private Category categoryId;

    @NotNull
    private String name;

}
