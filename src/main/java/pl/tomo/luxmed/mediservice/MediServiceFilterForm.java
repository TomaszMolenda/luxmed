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
    public String getClinicId() {
        return null;
    }

    @Override
    public String getServiceId() {
        return null;
    }

    @Override
    public String getPayerId() {
        return null;
    }
}
