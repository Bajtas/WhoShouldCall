package pl.bajtas.whoshouldring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.bajtas.whoshouldring.Service.UserService;
import pl.bajtas.whoshouldring.persistence.model.User;
import pl.bajtas.whoshouldring.util.Response;

/**
 * Created by Bajtas on 18.03.2017.
 */
@Controller
public class Index {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/index", "/", "/home"})
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = {"/register"})
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(value = "user") User user, ModelAndView modelAndView) {
        Response response = userService.register(user);
        modelAndView.addObject("response", "success");
        return "register";
    }
}
