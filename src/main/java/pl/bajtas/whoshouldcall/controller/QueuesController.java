package pl.bajtas.whoshouldcall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bajtas.whoshouldcall.service.baseData.BaseDataFiller;

/**
 * Created by Bajtas on 25.05.2017.
 */
@Controller
public class QueuesController {

    @RequestMapping(value="/queues", method= RequestMethod.GET)
    public String showQueuesPage(Model model) {
        new BaseDataFiller().fill(model);
        return "queues";
    }
}
