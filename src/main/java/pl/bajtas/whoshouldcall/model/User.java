package pl.bajtas.whoshouldcall.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Entity
@Table(name = "buser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    @ManyToOne
    @JoinColumn(name = "b_role")
    private UserRole userRole;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<QueueUser> queueUsers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Set<QueueUser> getQueueUsers() {
        return queueUsers;
    }

    public void setQueueUsers(Set<QueueUser> queueUsers) {
        this.queueUsers = queueUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equal(login, user.login) &&
                Objects.equal(userRole, user.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, login, password, userRole, queueUsers);
    }
}
