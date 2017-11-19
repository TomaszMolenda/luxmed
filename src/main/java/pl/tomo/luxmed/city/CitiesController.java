package pl.tomo.luxmed.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.storage.Storage;

@Controller
@RequestMapping("cities")
class CitiesController {

    private final CityQueries cityQueries;
    private final Storage storage;

    @Autowired
    CitiesController(CityQueries cityQueries, Storage storage) {
        this.cityQueries = cityQueries;
        this.storage = storage;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("cities", cityQueries.fetchData());

        return "cities";
    }

    @PostMapping
    private String post(@ModelAttribute("cityId") String cityId) {

        storage.setCityId(cityId);

        return "redirect:/services";
    }


}
