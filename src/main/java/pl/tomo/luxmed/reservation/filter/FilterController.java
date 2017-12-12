package pl.tomo.luxmed.reservation.filter;

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
@RequestMapping("filter")
class FilterController {

    private final Storage storage;
    private final FilterFactory filterFactory;
    private final FilterDtoFactory filterDtoFactory;

    @Autowired
    FilterController(Storage storage, FilterFactory filterFactory, FilterDtoFactory filterDtoFactory) {
        this.storage = storage;
        this.filterFactory = filterFactory;
        this.filterDtoFactory = filterDtoFactory;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute(new FilterForm());
        model.addAttribute(filterDtoFactory.create());

        return "filter";
    }

    @PostMapping
    private String post(@ModelAttribute("filterForm") FilterForm filterForm) {

        storage.setFilter(filterFactory.create(filterForm));

        return Redirect.builder()
                .path("/")
                .build();
    }
}
