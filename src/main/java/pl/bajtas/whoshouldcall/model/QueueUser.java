package pl.bajtas.whoshouldcall.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Entity
@Table(name = "bqueueuser")
public class QueueUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date lastCall;
    @ManyToOne
    @JoinColumn(name = "b_queue")
    private Queue queue;
    @ManyToOne
    @JoinColumn(name = "b_user")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLastCall() {
        return lastCall;
    }

    public void setLastCall(Date lastCall) {
        this.lastCall = lastCall;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
