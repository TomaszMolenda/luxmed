package pl.tomo.luxmed.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class LoginController {

    @GetMapping("/login")
    private String get(Model model) {

        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    @PostMapping("/login")
    private String post(@ModelAttribute LoginForm loginForm) {

        System.out.println(loginForm);

        return "redirect:/login";
    }
}
