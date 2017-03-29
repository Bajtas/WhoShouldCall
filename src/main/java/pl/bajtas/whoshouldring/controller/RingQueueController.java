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

import java.security.Principal;
import java.util.ArrayList;
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
    public String queueData(@RequestParam(value = "name", required = false, defaultValue = "") String queueName, Model model, Principal principal) {
        if (principal != null && StringUtils.isNotBlank(principal.getName())) {
            model.addAttribute("isModeratorOrAdmin", true);
            model.addAttribute("queueName", queueName);
        }

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

    @RequestMapping(value = "/queues", method = RequestMethod.GET)
    public String queueData(Model model, Principal principal) {
        if (principal != null)
            model.addAttribute("isLoggedIn", true);
        return "ringQueue";
    }

    @RequestMapping(value = "/queueManager", method = RequestMethod.GET)
    public String queueManagerData(@RequestParam(value = "name", required = false, defaultValue = "") String queueName, Model model, Principal principal) {
        if (StringUtils.isBlank(queueName)) {
            return "queueSelect";
        }

        List<User> users = Queue.toList(userService.getUsersRelatedWithQueue(queueName));
        List<User> usersForManagerForm = new ArrayList<>(users);
        ChartWrapper chartWrapper = Queue.processList(users);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String JSONDataSet = gson.toJson(chartWrapper);
        String chosenOnes = Queue.getChosenOnes(chartWrapper.getCategory());
        model.addAttribute("queueData", JSONDataSet);
        model.addAttribute("chosenOne", chosenOnes);

        Queue.sortAscEvenWithChosenOnes(usersForManagerForm);
        model.addAttribute("usersData", usersForManagerForm);

        return "queueManager";
    }
}
