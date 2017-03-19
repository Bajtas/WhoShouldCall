package pl.bajtas.whoshouldring.persistence.model;

import javax.persistence.*;

/**
 * Created by Bajtas on 18.03.2017.
 */
@Entity
@Table(name = "brole")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
