package pl.bajtas.whoshouldcall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import pl.bajtas.whoshouldcall.service.seeder.DataSeeder;
import pl.bajtas.whoshouldcall.repository.UserRoleRepository;

/**
 * Created by Bajtas on 13.05.2017.
 */
@Service
public class UserRoleService implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired UserRoleRepository userRoleRepository;
    @Autowired DataSeeder dataSeeder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        dataSeeder.seed();
    }
}
