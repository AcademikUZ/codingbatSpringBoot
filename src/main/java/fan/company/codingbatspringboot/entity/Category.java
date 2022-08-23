package fan.company.codingbatspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String annatation;

    @Column(nullable = false)
    private String text;

    private Integer rating;

    @ManyToOne
    private CategoryGlobal categoryGlobal;



}
