package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "unit_of_measure")
    @TableGenerator(name = "unit_of_measure", allocationSize = 1)
    private Long id;

    private String description;

}
