package pl.bajtas.whoshouldcall.service.baseData;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;

/**
 * Created by Bajtas on 23.05.2017.
 */
@Service
public class AuthDataFiller implements DataFiller {
    public void fill(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList(authentication.getAuthorities());
        Object principal = authentication.getPrincipal();
        String userLogin = "";
        if (principal instanceof User) {
            User user = (User) principal;
            userLogin = user.getUsername();
        } else if (principal instanceof String) {
            userLogin = (String) principal;
        }

        String auth = authorities.get(0).getAuthority();

        model.addAttribute("auth", auth);
        model.addAttribute("principal", userLogin);
    }
}
