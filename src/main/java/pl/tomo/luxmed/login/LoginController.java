package pl.tomo.luxmed.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class LoginController {

    private final UserInformationSaver userInformationSaver;
    private final LoginChecker loginChecker;

    @Autowired
    LoginController(UserInformationSaver userInformationSaver, LoginChecker loginChecker) {
        this.userInformationSaver = userInformationSaver;
        this.loginChecker = loginChecker;
    }

    @GetMapping("/login")
    private String get(Model model) {

        if (loginChecker.isLogged()) {

            return "main";
        }

        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    @PostMapping("/login")
    private String post(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes) {

        userInformationSaver.save(loginForm);

        if (loginChecker.isLogged()) {

            return "redirect:/main";
        }

        redirectAttributes.addFlashAttribute("error", "login is not possible");

        return "redirect:/login";
    }
}
