package pl.tomo.luxmed.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.storage.Storage;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static pl.tomo.luxmed.storage.Log.log;

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
        storage.addLog(log("Choose city, id:" + cityId + ", name: " + getCity(cityId)));

        return "redirect:/services";
    }

    private String getCity(String cityId) {

        return storage.getCities().stream()
                .filter(city -> city.getId().equalsIgnoreCase(cityId))
                .findAny()
                .map(City::getName)
                .orElse(EMPTY);
    }


}
