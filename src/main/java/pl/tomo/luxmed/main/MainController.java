package pl.tomo.luxmed.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

        model.addAttribute("mainForm", new MainForm());

        return "main";
    }

    @PostMapping
    private String post(@Valid @ModelAttribute MainForm mainForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "main";
        }

        return "redirect:/main";
    }

    @ModelAttribute
    private void modelAttribute(Model model) {

        model.addAllAttributes(modelGenerator.generate());
    }
}
