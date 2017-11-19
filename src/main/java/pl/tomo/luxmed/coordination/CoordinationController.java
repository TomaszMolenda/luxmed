package pl.tomo.luxmed.coordination;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.city.CitySaver;
import pl.tomo.luxmed.login.RequestVerificationTokenSaver;

@Controller
@RequestMapping("coordination")
class CoordinationController {

    private final CoordinationFetcher coordinationFetcher;
    private final ActivityApprover activityApprover;
    private final CitySaver citySaver;
    private final RequestVerificationTokenSaver requestVerificationTokenSaver;

    @Autowired
    CoordinationController(CoordinationFetcher coordinationFetcher, ActivityApprover activityApprover, CitySaver citySaver, RequestVerificationTokenSaver requestVerificationTokenSaver) {
        this.coordinationFetcher = coordinationFetcher;
        this.activityApprover = activityApprover;
        this.citySaver = citySaver;
        this.requestVerificationTokenSaver = requestVerificationTokenSaver;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("activities", coordinationFetcher.fetchActivities());

        return "coordination";
    }

    @PostMapping
    private String post(@ModelAttribute("url") String url) {

        final Document document = activityApprover.approve(url);

        citySaver.save(document);
        requestVerificationTokenSaver.save(document);

        return "redirect:/cities";
    }
}
