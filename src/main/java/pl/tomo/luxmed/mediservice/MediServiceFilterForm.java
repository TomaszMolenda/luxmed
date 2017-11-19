package pl.tomo.luxmed.mediservice;

import pl.tomo.luxmed.service.FilterForm;

import java.time.LocalDate;

class MediServiceFilterForm implements FilterForm {

    private final String cityId;

    MediServiceFilterForm(String cityId) {

        this.cityId = cityId;
    }

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
        return null;
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
    public String getTimeOption() {
        return null;
    }

    @Override
    public String getClinicId() {
        return null;
    }
}
