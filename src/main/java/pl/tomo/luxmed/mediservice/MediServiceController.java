package pl.tomo.luxmed.mediservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.Redirect;
import pl.tomo.luxmed.storage.Storage;

@Controller
@RequestMapping("services")
class MediServiceController {

    private final MediServiceFetcher mediServiceFetcher;
    private final Storage storage;

    @Autowired
    MediServiceController(MediServiceFetcher mediServiceFetcher, Storage storage) {
        this.mediServiceFetcher = mediServiceFetcher;
        this.storage = storage;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("services", mediServiceFetcher.fetch());

        return "services";
    }

    @PostMapping
    private String post(@ModelAttribute("serviceId") String serviceId) {

        storage.setServiceId(serviceId);

        return Redirect.builder()
                .path("/payers")
                .build();


    }
}
