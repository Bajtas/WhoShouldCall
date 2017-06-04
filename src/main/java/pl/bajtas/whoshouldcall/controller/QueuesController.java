package pl.bajtas.whoshouldcall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bajtas.whoshouldcall.service.QueueService;
import pl.bajtas.whoshouldcall.service.baseData.AuthDataFiller;

/**
 * Created by Bajtas on 25.05.2017.
 */
@Controller
public class QueuesController {
    @Autowired
    private AuthDataFiller authDataFiller;

    @Autowired
    private QueueService queueService;

    @RequestMapping(value="/queues", method= RequestMethod.GET)
    public String showMyQueuePage(Model model) {
        authDataFiller.fill(model);
        queueService.fillQueuesData(model);
        return "queues";
    }

    @RequestMapping(value="/queue/{queueName}", method= RequestMethod.GET)
    public String showQueuePage(Model model, @PathVariable("queueName") String queueName) {
        authDataFiller.fill(model);
        queueService.fillQueueData(model, queueName);
        return "queue";
    }
}
