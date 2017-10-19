package pl.tomo.luxmed.second;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/second")
class SecondStepController {

    @GetMapping
    private String get(Model model) {

        model.addAttribute("secondStepForm", new SecondStepForm());

        return "second";
    }

    @PostMapping
    private String post(@Valid @ModelAttribute SecondStepForm secondStepForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "second";
        }

        return "redirect:/second";
    }

    @ModelAttribute
    private void modelAttribute(Model model) {

        //model.addAllAttributes(modelGenerator.generate());
    }
}
