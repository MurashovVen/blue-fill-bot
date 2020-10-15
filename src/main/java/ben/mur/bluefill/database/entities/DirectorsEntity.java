package ben.mur.bluefill.database.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "directors", schema = "schema")
public class DirectorsEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
}
