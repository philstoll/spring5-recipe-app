package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "note")
    @TableGenerator(name = "note", allocationSize = 1)
    private Long id;

    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNote;

    public Note() {
    }

    public Note(String recipeNote) {
        this.recipeNote = recipeNote;
    }

}
