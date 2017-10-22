package pl.tomo.luxmed.second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.tomo.luxmed.summary.SummaryForm;

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

        model.addAttribute("summaryForm", new SummaryForm());

        FilterInformation filterInformation = new FilterInformation(cityId, serviceId, clinicId);
        model.addAllAttributes(secondModelGenerator.generate(filterInformation));
        model.addAllAttributes(secondModelGenerator.generate());

        return "second";
    }
}
