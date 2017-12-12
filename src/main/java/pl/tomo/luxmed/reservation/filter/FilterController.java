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

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("filter")
class FilterController {

    private final Storage storage;
    private final FilterFactory filterFactory;

    @Autowired
    FilterController(Storage storage, FilterFactory filterFactory) {
        this.storage = storage;
        this.filterFactory = filterFactory;
    }

    @GetMapping
    private String get(Model model) {

        final Filter filter = storage.getFilter();

        if (filter == null) {

            model.addAttribute(new FilterForm());

        } else {

            final LocalDate minimumDate = filter.getMinimumDate();
            final LocalTime minimumTime = filter.getMinimumTime();

            model.addAttribute(new FilterForm(minimumDate, minimumTime));
        }
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
