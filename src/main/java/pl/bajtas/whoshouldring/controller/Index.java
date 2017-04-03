package pl.bajtas.whoshouldring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bajtas.whoshouldring.Service.MailService;
import pl.bajtas.whoshouldring.Service.UserService;
import pl.bajtas.whoshouldring.persistence.model.User;
import pl.bajtas.whoshouldring.util.Response;

import java.security.Principal;

/**
 * Created by Bajtas on 18.03.2017.
 */
@Controller
public class Index {

    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;

    @RequestMapping(value = {"/index", "/", "/home"})
    public String index(Model model, Principal principal) {
        SecurityController.setIsLoggedIn(model, principal);
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(value = "user") User user, Model model) {
        Response response = userService.register(user);
        model.addAttribute("response", response);
        if (Response.Type.SUCCESS.equals(response.getType())) {
            return "login";
        }
        return "register";
    }
}
