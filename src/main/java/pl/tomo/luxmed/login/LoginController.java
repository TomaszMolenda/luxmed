package pl.tomo.luxmed.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    @PostMapping
    private String post(@ModelAttribute LoginForm loginForm) {

        userInformationSaver.save(loginForm);
        storageRefresher.refreshSimpleData();

        return "redirect:/first";
    }
}
