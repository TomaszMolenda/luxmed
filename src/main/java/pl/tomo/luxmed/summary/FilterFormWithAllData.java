package pl.tomo.luxmed.summary;

import lombok.Data;
import pl.tomo.luxmed.service.FilterForm;

import java.time.LocalDate;

@Data
class FilterFormWithAllData implements FilterForm {

    private String isFromStartPage;
    private String postId;
    private String payersCount;
    private String searchFirstFree;
    private String timeOption;
    private String cityId;
    private String clinicId;
    private String serviceId;
    private String doctorId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate dateFrom;
    private String payerId;

    @Override
    public String getCityId() {
        return cityId;
    }

    @Override
    public String getPostId() {
        return postId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    @Override
    public LocalDate getToDate() {
        return toDate;
    }

    @Override
    public String getDoctorId() {
        return doctorId;
    }

    @Override
    public String getPayerId() {
        return payerId;
    }

    @Override
    public String getPayersCount() {
        return payersCount;
    }

    @Override
    public String getIsFromStartPage() {
        return isFromStartPage;
    }

    @Override
    public String getSearchFirstFree() {
        return searchFirstFree;
    }

    @Override
    public String getTimeOption() {
        return timeOption;
    }

    @Override
    public String getClinicId() {
        return clinicId;
    }
}
