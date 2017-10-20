package pl.tomo.luxmed.summary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/summary")
class SummaryController {

    @GetMapping
    private String get(@RequestParam String cityId,
                       @RequestParam String serviceId,
                       @RequestParam String clinicId,
                       @RequestParam String doctorId,
                       @RequestParam String payerId, Model model) {

        return "summary";
    }
}
