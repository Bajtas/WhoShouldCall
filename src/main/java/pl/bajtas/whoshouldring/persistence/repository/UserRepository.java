package pl.bajtas.whoshouldring.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bajtas.whoshouldring.persistence.model.User;

/**
 * Created by Bajtas on 18.03.2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);

    User findByEmail(String email);

    User findByEmailContainsIgnoreCase(String email);
}
