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

import java.util.*;
import java.util.stream.Collectors;

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

    public void fillQueueData(Model model, String queueName) {
        Optional<Queue> queueByName = queueRepository.findByName(queueName);
        if (queueByName.isPresent()) {
            Queue queue = queueByName.get();
            Set<User> usersSet = queue.getUsers();
            if (usersSet != null) {
                List<User> users = new ArrayList<>(usersSet);
                List<User> usersWhosNotCallYet = users.stream().filter(u -> u.getLastCall() == null).collect(Collectors.toList());

                users.removeAll(usersWhosNotCallYet);

                users.sort((u1, u2) -> {
                    Date u1LastCall = u1.getLastCall();
                    Date u2lastCall = u2.getLastCall();

                    if (u1LastCall.before(u2lastCall))
                        return -1;
                    else if (u2lastCall.before(u1LastCall))
                        return 1;
                    else
                        return 0;
                });

                List<User> usersChosenToCall = getUsersChosenToCall(usersWhosNotCallYet, users);
                model.addAttribute("chosenOnes", usersChosenToCall);

                users.removeAll(usersChosenToCall);
                model.addAttribute("users", users);
            }
            model.addAttribute("queue", queue);
        }
    }

    private List<User> getUsersChosenToCall(List<User> usersWhosNotCallYet, List<User> users) {
        List<User> chosenOnes = new ArrayList<>();

        chosenOnes.addAll(usersWhosNotCallYet);
        if (users != null && users.size() > 0)
            chosenOnes.add(users.get(0));

        return chosenOnes;
    }

    public void fillQueuesData(Model model) {
        Iterable<Queue> queues = queueRepository.findAll();
        model.addAttribute("queues", queues);
    }
}
