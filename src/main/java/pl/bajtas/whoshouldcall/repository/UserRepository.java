package pl.bajtas.whoshouldcall.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bajtas.whoshouldcall.model.User;

import java.util.Optional;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
