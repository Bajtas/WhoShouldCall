package pl.bajtas.whoshouldring.Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bajtas.whoshouldring.config.Globals;
import pl.bajtas.whoshouldring.persistence.model.Role;
import pl.bajtas.whoshouldring.persistence.model.User;
import pl.bajtas.whoshouldring.persistence.repository.RoleRepository;
import pl.bajtas.whoshouldring.persistence.repository.UserRepository;
import pl.bajtas.whoshouldring.util.Response;

import java.util.Date;

/**
 * Created by Bajtas on 18.03.2017.
 */
@Service
public class UserService implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = Logger.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("Checking if default admin account exist.");

        if (userRepository.findByLogin(Globals.DEFAULT_ACCOUNT.ADMINISTRATOR_LOGIN) == null) {
            LOG.info("Default admin account not exist!");

            User administrator = new User();
            administrator.setLogin(Globals.DEFAULT_ACCOUNT.ADMINISTRATOR_LOGIN);
            administrator.setEmail(Globals.DEFAULT_ACCOUNT.ADMINISTRATOR_EMAIL);
            administrator.setOnline(false);
            administrator.setPassword(passwordEncoder.encode(Globals.DEFAULT_ACCOUNT.ADMINISTRATOR_PASSWORD));
            administrator.setLastOnline(new Date());
            Role adminRole = roleRepository.findByName(Globals.Roles.ADMINISTRATOR.toString());
            if (adminRole != null) {
                administrator.setRole(adminRole);
            }

            userRepository.save(administrator);
        } else
            LOG.info("Default admin account exist!");
    }

    public Response register(User user) {
        user.setOnline(false);
        user.setRole(roleRepository.findByName(Globals.Roles.USER.toString()));
        user.setLastOnline(new Date());
        Response ret = new Response();

        try {
            userRepository.save(user);

            ret.build(Response.Type.SUCCESS, "Registration successful!");
        } catch (Exception e) {
            LOG.error("Exception in UserService.register() - ", e);

            ret.build(Response.Type.ERROR, "Internal error.");
        }

        return ret;
    }
}
