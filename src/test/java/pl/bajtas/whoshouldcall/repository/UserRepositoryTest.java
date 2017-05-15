package pl.bajtas.whoshouldcall.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bajtas.whoshouldcall.config.AppConfig;
import pl.bajtas.whoshouldcall.config.PersistanceConfig;
import pl.bajtas.whoshouldcall.model.User;

import java.util.Optional;

/**
 * Created by Bajtas on 13.05.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PersistanceConfig.class, AppConfig.class})
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void addNewUserTest() {
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(bCryptPasswordEncoder);

        String password = "test";
        User user = new User();
        user.setLogin("test");

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        Assert.assertNotEquals("Password after encode should not be the same as input", password, encodedPassword);
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        Assert.assertNotNull(savedUser);
        Assert.assertNotNull(savedUser.getId());
        Assert.assertEquals(savedUser.getLogin(), user.getLogin());
        Assert.assertEquals(savedUser.getPassword(), user.getPassword());
    }

    @Test
    public void deleteUserTest() {
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(bCryptPasswordEncoder);

        String password = "test";
        User user = new User();
        user.setLogin("test");

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        Assert.assertNotEquals("Password after encode should not be the same as input", password, encodedPassword);
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        Assert.assertNotNull(savedUser);
        Assert.assertNotNull(savedUser.getId());
        Assert.assertEquals(savedUser.getLogin(), user.getLogin());
        Assert.assertEquals(savedUser.getPassword(), user.getPassword());

        int id = user.getId();
        userRepository.delete(user);
        Optional<User> deletedUser = userRepository.findById(id);
        Assert.assertFalse(deletedUser.isPresent());
    }

    @Test
    public void updateUserTest() {
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(bCryptPasswordEncoder);

        String password = "test";
        User user = new User();
        user.setLogin("test");

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        Assert.assertNotEquals("Password after encode should not be the same as input", password, encodedPassword);
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        Assert.assertNotNull(savedUser);
        Assert.assertNotNull(savedUser.getId());
        Assert.assertEquals(savedUser.getLogin(), user.getLogin());
        Assert.assertEquals(savedUser.getPassword(), user.getPassword());

        savedUser.setLogin("test2");
        userRepository.save(savedUser);

        Optional<User> userFromDB = userRepository.findById(savedUser.getId());
        Assert.assertTrue(userFromDB.isPresent());
        Assert.assertEquals(savedUser, userFromDB.get());
    }
}
