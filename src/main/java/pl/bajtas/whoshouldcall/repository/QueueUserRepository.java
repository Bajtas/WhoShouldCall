package pl.bajtas.whoshouldcall.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bajtas.whoshouldcall.model.QueueUser;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Repository
public interface QueueUserRepository extends CrudRepository<QueueUser, Integer> {

}
