package pl.bajtas.whoshouldring.Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import pl.bajtas.whoshouldring.config.Globals;
import pl.bajtas.whoshouldring.persistence.model.Role;
import pl.bajtas.whoshouldring.persistence.repository.RoleRepository;

import java.util.Arrays;

/**
 * Created by Bajtas on 19.03.2017.
 */
@Service
public class RoleService implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = Logger.getLogger(RoleService.class);
    private static final Globals.Roles[] DEFAULT_ROLES = new Globals.Roles[]{Globals.Roles.ADMINISTRATOR, Globals.Roles.USER, Globals.Roles.USER.MODERATOR};
    @Autowired
    private RoleRepository userRoleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Insert default roles to DB if they does not exist.
        LOG.info("Check if there are proper roles defined in DB.");

        int rolesInDBCounter = 0;
        for (Globals.Roles defaultRole : DEFAULT_ROLES) {
            String name = defaultRole.toString();
            if (userRoleRepository.findByName(name) == null) {
                LOG.info("-> Role with name: " + name + " not found!\n\t-> Adding this role to DB...");
                Role role = new Role();
                role.setName(role.toString());

                userRoleRepository.save(role);
                LOG.info("Role with name: {" + name + "} added!");
            } else
                rolesInDBCounter++;
        }

        if (rolesInDBCounter == DEFAULT_ROLES.length) {
            LOG.info("Success! Roles: " + Arrays.toString(DEFAULT_ROLES) + " are in DB!");
        }
    }
}
