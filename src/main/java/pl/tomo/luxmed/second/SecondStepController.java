package pl.tomo.luxmed.second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/second")
class SecondStepController {

    private final SecondModelGenerator secondModelGenerator;

    @Autowired
    SecondStepController(SecondModelGenerator secondModelGenerator) {
        this.secondModelGenerator = secondModelGenerator;
    }

    @GetMapping
    private String get(@RequestParam String cityId,
                       @RequestParam String serviceId,
                       @RequestParam String clinicId, Model model) {

        model.addAttribute("secondStepForm", new SecondStepForm());

        FilterInformation filterInformation = new FilterInformation(cityId, serviceId, clinicId);
        model.addAllAttributes(secondModelGenerator.generate(filterInformation));

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
