package pl.bajtas.whoshouldcall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bajtas.whoshouldcall.service.UserService;
import pl.bajtas.whoshouldcall.service.baseData.BaseDataHandler;

/**
 * Created by Bajtas on 16.05.2017.
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showHomePage(Model model) {
        new BaseDataHandler().fill(model);


        return "index";
    }
}
