package pl.bajtas.whoshouldcall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.bajtas.whoshouldcall.config.Globals;
import pl.bajtas.whoshouldcall.model.User;
import pl.bajtas.whoshouldcall.model.UserRole;
import pl.bajtas.whoshouldcall.repository.UserRepository;
import pl.bajtas.whoshouldcall.repository.UserRoleRepository;
import pl.bajtas.whoshouldcall.service.seeder.DataSeeder;
import pl.bajtas.whoshouldcall.util.exception.DefaultUserRoleNotFound;

import java.util.Optional;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Service
public class UserService implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired DataSeeder dataSeeder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        dataSeeder.seed();
    }

    public void registerNewUser(Model model, User user) throws DefaultUserRoleNotFound {
        Optional<User> existingUser = userRepository.findByLogin(user.getLogin());
        if (existingUser.isPresent()) {
            model.addAttribute("error", "User with login: " + user.getLogin() + " exist!");
            return;
        }

        UserRole userRole = userRoleRepository.findByName(Globals.USER_ROLES.USER.toString()).orElseThrow(DefaultUserRoleNotFound::new);
        user.setUserRole(userRole);
        user.setPassword(hashPassword(user.getPassword()));

        userRepository.save(user);
    }

    private String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
