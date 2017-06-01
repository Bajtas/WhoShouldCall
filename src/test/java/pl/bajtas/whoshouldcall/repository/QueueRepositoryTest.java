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
import pl.bajtas.whoshouldcall.model.Queue;
import pl.bajtas.whoshouldcall.model.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Bajtas on 15.05.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PersistanceConfig.class, AppConfig.class})
public class QueueRepositoryTest {
    @Autowired
    QueueRepository queueRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void addNewQueue() {
        Assert.assertNotNull(queueRepository);
        Queue queue = new Queue();

        queue.setName("test");
        queue.setCompanyName("test");

        Queue save = queueRepository.save(queue);

        Assert.assertNotNull(save);
        Assert.assertTrue(save.getId() >= 0);
        Assert.assertNull(queue.getUsers());
        Assert.assertEquals(queue.getName(), save.getName());
        Assert.assertEquals(queue.getCompanyName(), save.getCompanyName());
    }

    @Test
    public void addNewQueueWithUsers() {
        Assert.assertNotNull(queueRepository);
        Queue queue = new Queue();
        queue.setName("test");
        queue.setCompanyName("test");

        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setLastCall(new Date());

        User savedUser = userRepository.save(user);
        Assert.assertNotNull(savedUser);
        Assert.assertTrue(savedUser.getId() >= 0);

        Set<User> users = new HashSet<>();
        savedUser.setQueue(queue);
        users.add(savedUser);

        queue.setUsers(users);

        userRepository.save(savedUser);
        Assert.assertNotNull(savedUser.getQueue());
        Assert.assertNotNull(savedUser.getQueue().getUsers());
        Assert.assertEquals(1, savedUser.getQueue().getUsers().size());

        Optional<User> byLogin = userRepository.findByLogin(user.getLogin());

        Assert.assertTrue(byLogin.isPresent());
        Assert.assertNotNull(byLogin.get().getQueue());
    }
}
