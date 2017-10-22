package pl.tomo.luxmed.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomo.luxmed.reservation.ReservationStarter;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/summary")
class SummaryController {

    private final ReservationStarter reservationStarter;

    @Autowired
    SummaryController(ReservationStarter reservationStarter) {
        this.reservationStarter = reservationStarter;
    }

    @PostMapping
    private String post(@Valid @ModelAttribute SummaryForm summaryForm) {

        reservationStarter.start(create(summaryForm));

        return "summary";
    }

    private FilterFormWithAllData create(SummaryForm summaryForm) {

        FilterFormWithAllData filterFormWithAllData = new FilterFormWithAllData();

        filterFormWithAllData.setCityId(summaryForm.getCityId());
        filterFormWithAllData.setClinicId(summaryForm.getClinicId());
        filterFormWithAllData.setDateFrom(LocalDate.now());
        filterFormWithAllData.setFromDate(LocalDate.now());
        filterFormWithAllData.setToDate(LocalDate.now());
        filterFormWithAllData.setTimeOption("Any");
        filterFormWithAllData.setDoctorId(summaryForm.getDoctorId());
        filterFormWithAllData.setIsFromStartPage("True");
        filterFormWithAllData.setPayerId(summaryForm.getPayerId());
        filterFormWithAllData.setPayersCount("0");
        filterFormWithAllData.setSearchFirstFree("True");
        filterFormWithAllData.setServiceId(summaryForm.getServiceId());

        return filterFormWithAllData;
    }
}
