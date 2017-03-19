package pl.bajtas.whoshouldring.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Bajtas on 18.03.2017.
 */
public class SecurityController {
    @RequestMapping("/login")
    public String login(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
