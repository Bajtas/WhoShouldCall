package pl.bajtas.whoshouldring.persistence.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bajtas on 18.03.2017.
 */
@Entity
@Table(name = "bringqueue")
public class RingQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date lmod;
    private Date createdOn;
}
