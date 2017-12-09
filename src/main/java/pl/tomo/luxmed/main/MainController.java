package pl.tomo.luxmed.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class MainController {


   @GetMapping
    private String get(Model model) {

        return "main";
    }
}
