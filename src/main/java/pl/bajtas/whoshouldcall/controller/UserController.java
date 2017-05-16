package pl.bajtas.whoshouldcall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.bajtas.whoshouldcall.service.UserService;

/**
 * Created by Bajtas on 15.05.2017.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/{username}", method= RequestMethod.GET)
    public String showUserPage(@PathVariable String username, Model model) {
        return "asdas";
    }
}
