package pl.tomo.luxmed.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.tomo.luxmed.storage.StorageRefresher;

@Controller
@RequestMapping("/login")
class LoginController {

    private final UserInformationSaver userInformationSaver;
    private final LoginChecker loginChecker;
    private final StorageRefresher storageRefresher;

    @Autowired
    LoginController(UserInformationSaver userInformationSaver, LoginChecker loginChecker, StorageRefresher storageRefresher) {
        this.userInformationSaver = userInformationSaver;
        this.loginChecker = loginChecker;
        this.storageRefresher = storageRefresher;
    }

    @GetMapping
    private String get(Model model) {

        if (loginChecker.isLogged()) {

            return "first";
        }

        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    @PostMapping
    private String post(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes) {

        userInformationSaver.save(loginForm);

        if (loginChecker.isLogged()) {

            storageRefresher.refreshSimpleData();
            return "redirect:/first";
        }

        redirectAttributes.addFlashAttribute("error", "login is not possible");

        return "redirect:/login";
    }
}
