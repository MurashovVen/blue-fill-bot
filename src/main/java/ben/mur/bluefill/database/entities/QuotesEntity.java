package ben.mur.bluefill.database.entities;


import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quotes", schema = "schema")
public class QuotesEntity {
    @Id
    @Column(name = "id")
    private int quoteId;
    @Basic
    @Column(name = "quote")
    private String quote;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "movie_name", referencedColumnName = "name")
    private MoviesEntity movies;
}
