package pl.bajtas.whoshouldring.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bajtas.whoshouldring.persistence.model.RingQueue;

/**
 * Created by Bajtas on 18.03.2017.
 */
@Repository
public interface RingQueueRepository extends CrudRepository<RingQueue, Integer> {

}
