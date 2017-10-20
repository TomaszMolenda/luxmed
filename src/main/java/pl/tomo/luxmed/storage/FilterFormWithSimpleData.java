package pl.tomo.luxmed.storage;

import lombok.AllArgsConstructor;
import pl.tomo.luxmed.service.FilterForm;

import java.time.LocalDate;

@AllArgsConstructor
class FilterFormWithSimpleData implements FilterForm {

    private final String cityId;
    private final String clinicId;
    private final String serviceId;

    @Override
    public String getCityId() {
        return cityId;
    }

    @Override
    public String getPostId() {
        return null;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public LocalDate getDateFrom() {
        return null;
    }

    @Override
    public LocalDate getToDate() {
        return null;
    }

    @Override
    public String getDoctorId() {
        return null;
    }

    @Override
    public String getPayerId() {
        return null;
    }

    @Override
    public String getPayersCount() {
        return null;
    }

    @Override
    public String getIsFromStartPage() {
        return null;
    }

    @Override
    public String getSearchFirstFree() {
        return null;
    }

    @Override
    public String getRequestVerificationToken() {
        return null;
    }

    @Override
    public String getTimeOption() {
        return null;
    }

    @Override
    public String getClinicId() {
        return clinicId;
    }
}
