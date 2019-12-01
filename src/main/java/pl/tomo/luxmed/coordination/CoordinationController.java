package pl.tomo.luxmed.coordination;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.storage.Storage;

import java.util.List;

@Controller
@RequestMapping("coordination")
class CoordinationController {

    private final CoordinationFetcher coordinationFetcher;
    private final ActivityApprover activityApprover;
    private final Storage storage;

    @Autowired
    CoordinationController(CoordinationFetcher coordinationFetcher, ActivityApprover activityApprover, Storage storage) {
        this.coordinationFetcher = coordinationFetcher;
        this.activityApprover = activityApprover;
        this.storage = storage;
    }

    @GetMapping("init")
    private String init() {

        coordinationFetcher.fetchActivities();

        return "redirect:/coordination";
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("activities", storage.getCoordinationActivities());

        return "coordination";
    }

    @PostMapping
    private String post(@ModelAttribute("url") String url) {

        List<CoordinationActivity> activities = activityApprover.approve(url);

        if (activities.isEmpty()) {

            String activityId = StringUtils.substringAfter(url, "actionId=");

            return "redirect:/cities?activityId=" + activityId;
        }

        return "redirect:/coordination";
    }
}
