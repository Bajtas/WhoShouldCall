package pl.bajtas.whoshouldcall.service.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.bajtas.whoshouldcall.model.User;
import pl.bajtas.whoshouldcall.model.UserRole;
import pl.bajtas.whoshouldcall.repository.UserRepository;
import pl.bajtas.whoshouldcall.repository.UserRoleRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Bajtas on 23.05.2017.
 */
@Component
public class UserSeeder implements DbSeeder{
    @Autowired UserRepository userRepository;
    @Autowired UserRoleRepository userRoleRepository;
    @Autowired BCryptPasswordEncoder passwordEncoder;

    @Override
    public void seed() {
        Optional<UserRole> admin = userRoleRepository.findByName("Admin");
        Optional<UserRole> moderator = userRoleRepository.findByName("Moderator");
        Optional<UserRole> user = userRoleRepository.findByName("User");

        Set<User> users = new HashSet<>();
        if (!isUserExist("admin"))
            users.add(new User("admin", passwordEncoder.encode("root"), admin.get()));
        if (!isUserExist("mod"))
            users.add(new User("mod", passwordEncoder.encode("root"), moderator.get()));
        if (!isUserExist("user"))
            users.add(new User("user", passwordEncoder.encode("root"), user.get()));
        userRepository.saveAll(users);
    }

    private boolean isUserExist(String userLogin) {
        return userRepository.findByLogin(userLogin).isPresent();
    }
}
