package pl.bajtas.whoshouldring.Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bajtas.whoshouldring.config.Globals;
import pl.bajtas.whoshouldring.persistence.model.User;
import pl.bajtas.whoshouldring.persistence.repository.RoleRepository;
import pl.bajtas.whoshouldring.persistence.repository.UserRepository;
import pl.bajtas.whoshouldring.util.Response;

/**
 * Created by Bajtas on 18.03.2017.
 */
@Service
public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public Response register(User user) {
        user.setOnline(false);
        user.setRole(roleRepository.findByName(Globals.Roles.USER.toString()));

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
