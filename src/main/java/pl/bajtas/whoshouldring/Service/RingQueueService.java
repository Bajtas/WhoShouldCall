package pl.bajtas.whoshouldring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bajtas.whoshouldring.persistence.repository.RingQueueRepository;

/**
 * Created by Bajtas on 19.03.2017.
 */
@Service
public class RingQueueService {
    @Autowired
    RingQueueRepository ringQueueRepository;
}
