package pl.bajtas.whoshouldring.persistence.model;

import javax.persistence.*;
import java.util.Set;

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
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
