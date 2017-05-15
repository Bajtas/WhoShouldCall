package pl.bajtas.whoshouldcall.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Entity
@Table(name = "brole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @OneToMany(mappedBy = "userRole", cascade = CascadeType.PERSIST)
    private Set<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
