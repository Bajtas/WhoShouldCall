package pl.bajtas.whoshouldcall.service.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bajtas.whoshouldcall.model.UserRole;
import pl.bajtas.whoshouldcall.repository.UserRoleRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bajtas on 23.05.2017.
 */
@Component
public class UserRoleSeeder implements DbSeeder {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public void seed() {
        Set<UserRole> userRoles = new HashSet<>();
        prepareDefaultRoles(userRoles);
        userRoleRepository.saveAll(userRoles);
    }

    private void prepareDefaultRoles(Set<UserRole> userRoles) {
        if (!isRoleExist("Admin"))
            userRoles.add(new UserRole("Admin"));
        if (!isRoleExist("Moderator"))
            userRoles.add(new UserRole("Moderator"));
        if (!isRoleExist("User"))
            userRoles.add(new UserRole("User"));
    }

    private boolean isRoleExist(String roleName) {
        UserRole role = userRoleRepository.findByName(roleName);
        return role != null;
    }
}
