package pl.bajtas.whoshouldcall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.bajtas.whoshouldcall.model.Queue;
import pl.bajtas.whoshouldcall.model.User;
import pl.bajtas.whoshouldcall.repository.QueueRepository;
import pl.bajtas.whoshouldcall.repository.UserRepository;

import java.util.Optional;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Service
public class QueueService implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QueueRepository queueRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Optional<User> admin = userRepository.findByLogin("admin");

        if (admin.isPresent()) {
            Queue queue = new Queue();
            queue.setName("testAdminQueue");


        }
    }

    public void fillQueueData(Model model, String userLoginFromAuthData) {
        Optional<User> userByLogin = userRepository.findByLogin(userLoginFromAuthData);
        if (userByLogin.isPresent()) {
            User user = userByLogin.get();
        }
    }
}
