package pl.tomo.luxmed.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
class MainController {

    private final ModelGenerator modelGenerator;

    @Autowired
    MainController(ModelGenerator modelGenerator) {
        this.modelGenerator = modelGenerator;
    }

    @GetMapping
    private String get(Model model) {

        model.addAllAttributes(modelGenerator.generate());

        return "main";
    }
}
