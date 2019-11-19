package pl.tomo.luxmed.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.reservation.ReservationStarter;
import pl.tomo.luxmed.storage.Storage;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/summary")
class SummaryController {

    private final Storage storage;
    private final ReservationStarter reservationStarter;

    @Autowired
    SummaryController(Storage storage, ReservationStarter reservationStarter) {
        this.storage = storage;
        this.reservationStarter = reservationStarter;
    }

    @GetMapping
    private String get(Model model) {

        model.addAttribute("clinicId", null);
        model.addAttribute("serviceId", storage.getServiceId());
        model.addAttribute("cityId", storage.getCityId());
        model.addAttribute("doctorId", null);
        model.addAttribute("payerId", storage.getPayerId());

        return "summary";
    }

    @PostMapping
    private String post(@Valid @ModelAttribute SummaryForm summaryForm) {

        reservationStarter.start(create(summaryForm));

        return "redirect:/";
    }

    private FilterFormWithAllData create(SummaryForm summaryForm) {

        FilterFormWithAllData filterFormWithAllData = new FilterFormWithAllData();

        filterFormWithAllData.setCityId(summaryForm.getCityId());
        filterFormWithAllData.setServiceId(summaryForm.getServiceId());
        filterFormWithAllData.setPayerId(summaryForm.getPayerId());
        filterFormWithAllData.setPayerId(summaryForm.getPayerId());

        return filterFormWithAllData;
    }
}
