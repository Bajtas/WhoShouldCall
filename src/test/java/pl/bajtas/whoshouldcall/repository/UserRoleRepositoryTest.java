package pl.bajtas.whoshouldcall.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bajtas.whoshouldcall.config.AppConfig;
import pl.bajtas.whoshouldcall.config.PersistanceConfig;
import pl.bajtas.whoshouldcall.model.User;
import pl.bajtas.whoshouldcall.model.UserRole;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Bajtas on 15.05.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PersistanceConfig.class, AppConfig.class})
public class UserRoleRepositoryTest {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void addNewRole() {
        Assert.assertNotNull(userRoleRepository);

        UserRole userRole = new UserRole();
        userRole.setName("Administrator");

        UserRole save = userRoleRepository.save(userRole);

        Assert.assertNotNull(save);
        Assert.assertNotNull(save.getId());
        Assert.assertEquals(userRole.getName(), save.getName());
    }

    @Test
    public void addNewRoleWithUsersAssigned() {
        Assert.assertNotNull(userRoleRepository);

        UserRole userRole = new UserRole();
        userRole.setName("Administrator");
        User user = new User();
        user.setLogin("test1");
        user.setPassword("test1");
        User user2 = new User();
        user2.setLogin("test2");
        user2.setPassword("test2");

        Set<User> users = new HashSet<>();
        users.add(user);
        users.add(user2);

        userRole.setUsers(users);

        UserRole save = userRoleRepository.save(userRole);

        Assert.assertNotNull(save);
        Assert.assertNotNull(save.getId());
        Assert.assertEquals(userRole.getName(), save.getName());
        Assert.assertEquals(2, save.getUsers().size());
    }

    @Test
    public void updateUserRole() {
        Assert.assertNotNull(userRoleRepository);

        UserRole userRole = new UserRole();
        userRole.setName("Administrator2");

        UserRole save = userRoleRepository.save(userRole);

        Assert.assertNotNull(save);
        Assert.assertNotNull(save.getId());
        Assert.assertEquals(userRole.getName(), save.getName());

        save.setName("potato");
        userRoleRepository.save(save);

        Optional<UserRole> byName = userRoleRepository.findByName(save.getName());
        Assert.assertTrue(byName.isPresent());
        Assert.assertEquals(save.getName(), byName.get().getName());
    }

    @Test
    public void deleteNewRoleWithUsersAssigned() {
        Assert.assertNotNull(userRoleRepository);

        UserRole userRole = new UserRole();
        userRole.setName("Administrator");
        User user = new User();
        user.setLogin("test3");
        user.setPassword("test3");
        User user2 = new User();
        user2.setLogin("test4");
        user2.setPassword("test4");

        Set<User> users = new HashSet<>();
        users.add(user);
        users.add(user2);

        userRole.setUsers(users);

        UserRole save = userRoleRepository.save(userRole);

        Assert.assertNotNull(save);
        Assert.assertNotNull(save.getId());
        Assert.assertEquals(userRole.getName(), save.getName());
        Assert.assertEquals(2, save.getUsers().size());

        userRoleRepository.delete(save);

        Optional<User> test1 = userRepository.findByLogin("test3");
        Optional<User> test2 = userRepository.findByLogin("test4");

        Assert.assertTrue(test1.isPresent());
        Assert.assertTrue(test2.isPresent());
    }
}
