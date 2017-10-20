package pl.tomo.luxmed.second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.tomo.luxmed.Redirect;

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
        model.addAllAttributes(secondModelGenerator.generate());

        return "second";
    }

    @PostMapping
    private String post(@Valid @ModelAttribute SecondStepForm secondStepForm, BindingResult bindingResult,
                        Model model) {

        if (bindingResult.hasErrors()) {

            return "second";
        }

        return Redirect.builder()
                .path("summary")
                .param("cityId", secondStepForm.getCityId())
                .param("serviceId", secondStepForm.getServiceId())
                .param("clinicId", secondStepForm.getClinicId())
                .param("doctorId", secondStepForm.getDoctorId())
                .param("payerId", secondStepForm.getPayerId())
                .build();
    }

    @ModelAttribute
    private void modelAttribute(Model model) {

        //model.addAllAttributes(modelGenerator.generate());
    }
}
