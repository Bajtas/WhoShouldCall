package pl.bajtas.whoshouldring.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Created by Bajtas on 18.03.2017.
 */
public class SecurityController {
    public static void setIsLoggedIn(Model model, Principal principal) {
        if (principal != null)
            model.addAttribute("isLoggedIn", true);
        else
            model.addAttribute("isLoggedIn", false);
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
