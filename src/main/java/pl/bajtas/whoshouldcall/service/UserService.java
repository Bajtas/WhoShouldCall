package pl.bajtas.whoshouldcall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import pl.bajtas.whoshouldcall.repository.UserRepository;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Service
public class UserService implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
