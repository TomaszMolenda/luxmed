package pl.tomo.luxmed.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.tomo.luxmed.storage.Storage;

@Controller
class MainController {

    private final Storage storage;

    @Autowired
    MainController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("logs", storage.getLogs());

        return "main";
    }
}
