package pl.bajtas.whoshouldring.persistence.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bajtas on 18.03.2017.
 */
@Entity
@Table(name = "buser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String email;
    private String password;
    private Date lastOnline;
    private Date lastCall;
    private Boolean isOnline;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Date lastOnline) {
        this.lastOnline = lastOnline;
    }

    public Date getLastCall() {
        return lastCall;
    }

    public void setLastCall(Date lastCall) {
        this.lastCall = lastCall;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
