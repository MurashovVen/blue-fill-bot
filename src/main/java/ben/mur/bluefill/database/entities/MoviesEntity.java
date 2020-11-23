package ben.mur.bluefill.database.entities;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movies", schema = "schema")
public class MoviesEntity {
    @Id
    @Column(name = "name")
    private String name;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "directors_id", referencedColumnName = "id")
    private DirectorsEntity directors;
}
