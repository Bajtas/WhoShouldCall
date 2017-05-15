package pl.bajtas.whoshouldcall.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Entity
@Table(name = "bqueue")
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String companyName;
    @OneToMany(mappedBy = "queue", cascade = CascadeType.ALL)
    private Set<QueueUser> queueUsers;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<QueueUser> getQueueUsers() {
        return queueUsers;
    }

    public void setQueueUsers(Set<QueueUser> queueUsers) {
        this.queueUsers = queueUsers;
    }
}
