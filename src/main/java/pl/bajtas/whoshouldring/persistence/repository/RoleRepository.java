package pl.bajtas.whoshouldring.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import pl.bajtas.whoshouldring.persistence.model.Role;

/**
 * Created by Bajtas on 19.03.2017.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
