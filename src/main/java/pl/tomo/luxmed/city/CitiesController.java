package pl.tomo.luxmed.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.storage.Storage;

import static pl.tomo.luxmed.storage.Log.log;

@Controller
@RequestMapping("cities")
class CitiesController {

    private final CityQueries cityQueries;
    private final Storage storage;
    private final CitySaver citySaver;

    @Autowired
    CitiesController(CityQueries cityQueries, Storage storage, CitySaver citySaver) {
        this.cityQueries = cityQueries;
        this.storage = storage;
        this.citySaver = citySaver;
    }

    @GetMapping
    private String get(@ModelAttribute("activityId") String activityId, Model model) {

        citySaver.save(activityId);

        model.addAttribute("cities", cityQueries.fetchData());

        return "cities";
    }

    @PostMapping
    private String post(@ModelAttribute("cityId") String cityId) {

        storage.setCityId(cityId);

        storage.addLog(log("Choose city, id:" + cityId + ", name: " + cityQueries.getData(cityId)));

        return "redirect:/services";
    }




}
