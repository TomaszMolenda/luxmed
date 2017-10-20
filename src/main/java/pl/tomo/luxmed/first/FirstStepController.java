package pl.tomo.luxmed.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.Redirect;
import pl.tomo.luxmed.storage.StorageRefresher;

import javax.validation.Valid;

@Controller
@RequestMapping("/first")
class FirstStepController {

    private final FirstModelGenerator firstModelGenerator;
    private final StorageRefresher storageRefresher;

    @Autowired
    FirstStepController(FirstModelGenerator firstModelGenerator, StorageRefresher storageRefresher) {
        this.firstModelGenerator = firstModelGenerator;
        this.storageRefresher = storageRefresher;
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

        storageRefresher.refreshAdvancedData(firstStepForm);

        return Redirect.builder()
                .path("/second")
                .param("cityId", firstStepForm.getCityId())
                .param("serviceId", firstStepForm.getServiceId())
                .param("clinicId", firstStepForm.getClinicId())
                .build();
    }

    @ModelAttribute
    private void modelAttribute(Model model) {

        model.addAllAttributes(firstModelGenerator.generate());
    }
}
