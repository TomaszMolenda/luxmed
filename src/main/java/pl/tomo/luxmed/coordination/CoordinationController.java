package pl.tomo.luxmed.coordination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("coordination")
class CoordinationController {

    private final CoordinationFetcher coordinationFetcher;

    @Autowired
    CoordinationController(CoordinationFetcher coordinationFetcher) {
        this.coordinationFetcher = coordinationFetcher;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("activities", coordinationFetcher.fetchActivities());

        return "coordination";
    }

    @PostMapping
    private String post(@ModelAttribute("url") String url) {



        return "redirect:/test";
    }
}
