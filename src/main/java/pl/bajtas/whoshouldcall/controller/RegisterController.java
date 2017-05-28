package pl.bajtas.whoshouldcall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bajtas.whoshouldcall.model.User;
import pl.bajtas.whoshouldcall.service.UserService;
import pl.bajtas.whoshouldcall.service.baseData.AuthDataFiller;
import pl.bajtas.whoshouldcall.util.exception.DefaultUserRoleNotFound;

/**
 * Created by Bajtas on 25.05.2017.
 */
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthDataFiller authDataFiller;

    @ModelAttribute(value = "user")
    public User newUser()
    {
        return new User();
    }

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String showRegisterPage(Model model) {
        authDataFiller.fill(model);
        return "register";
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String showRegisterPage(Model model, @ModelAttribute("user") User user) throws DefaultUserRoleNotFound {
        userService.registerNewUser(model, user);
        return "register";
    }
}
