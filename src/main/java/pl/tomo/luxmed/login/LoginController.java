package pl.tomo.luxmed.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
class LoginController {

    private final UserInformationSaver userInformationSaver;

    @Autowired
    LoginController(UserInformationSaver userInformationSaver) {
        this.userInformationSaver = userInformationSaver;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    @PostMapping
    private String post(@ModelAttribute LoginForm loginForm) {

        userInformationSaver.save(loginForm);

        return "redirect:/coordination/init";
    }
}
