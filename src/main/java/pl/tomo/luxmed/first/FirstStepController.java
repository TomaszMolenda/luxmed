package pl.tomo.luxmed.first;

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
@RequestMapping("/first")
class FirstStepController {

    private final ModelGenerator modelGenerator;

    @Autowired
    FirstStepController(ModelGenerator modelGenerator) {
        this.modelGenerator = modelGenerator;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("firstStepForm", new FirstStepForm());

        return "first";
    }

    @PostMapping
    private String post(@Valid @ModelAttribute FirstStepForm firstStepForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "first";
        }

        return "redirect:/first";
    }

    @ModelAttribute
    private void modelAttribute(Model model) {

        model.addAllAttributes(modelGenerator.generate());
    }
}
