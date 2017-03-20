package pl.bajtas.whoshouldring.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bajtas.whoshouldring.Service.RingQueueService;
import pl.bajtas.whoshouldring.Service.UserService;
import pl.bajtas.whoshouldring.persistence.model.User;
import pl.bajtas.whoshouldring.storage.queue.ChartWrapper;
import pl.bajtas.whoshouldring.util.Queue;

import java.util.List;

/**
 * Created by Bajtas on 19.03.2017.
 */
@Controller
public class RingQueueController {
    @Autowired
    RingQueueService ringQueueService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/queue", method = RequestMethod.GET)
    public String queueData(@RequestParam(value = "name", required = false, defaultValue = "") String queueName, Model model) {
        if (StringUtils.isBlank(queueName)) {
            return "queueSelect";
        }
        List<User> users = Queue.toList(userService.getUsersRelatedWithQueue(queueName));
        ChartWrapper chartWrapper = Queue.processList(users);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String JSONDataSet = gson.toJson(chartWrapper);
        String chosenOnes = Queue.getChosenOnes(chartWrapper.getCategory());
        model.addAttribute("queueData", JSONDataSet);
        model.addAttribute("chosenOne", chosenOnes);

        return "ringQueue";
    }
}
