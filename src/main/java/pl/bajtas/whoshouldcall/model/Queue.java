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
    private String company_name;
    @OneToMany(mappedBy = "queue")
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
