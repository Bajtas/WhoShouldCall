package pl.bajtas.whoshouldring.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.bajtas.whoshouldring.persistence.model.RingQueue;
import pl.bajtas.whoshouldring.persistence.model.User;
import pl.bajtas.whoshouldring.persistence.repository.RingQueueRepository;
import pl.bajtas.whoshouldring.persistence.repository.UserRepository;

import java.security.Principal;

/**
 * Created by Bajtas on 19.03.2017.
 */
@Service
public class RingQueueService {
    @Autowired
    RingQueueRepository ringQueueRepository;
    @Autowired
    UserRepository userRepository;

    public String getUserQueue(Principal principal) {
        String userName = principal.getName();

        User user = userRepository.findByLogin(userName);

        return user != null && user.getRingQueue() != null ? user.getRingQueue().getName() : StringUtils.EMPTY;
    }

    public Page<RingQueue> getRingQueues(int page, int size) {
        Page<RingQueue> queues = ringQueueRepository.findAll(new PageRequest(page, size));

        return queues;
    }
}
