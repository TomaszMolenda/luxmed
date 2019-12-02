package pl.tomo.luxmed.payers;

import pl.tomo.luxmed.service.FilterForm;

import java.time.LocalDate;

class PayerFilterForm implements FilterForm {

    private final String cityId;
    private final String serviceId;
    private final String coordinationActivityId;

    PayerFilterForm(String cityId, String serviceId, String coordinationActivityId) {

        this.cityId = cityId;
        this.serviceId = serviceId;
        this.coordinationActivityId = coordinationActivityId;
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
        return serviceId;
    }

    @Override
    public String getPayerId() {
        return null;
    }

    @Override
    public String getCoordinationActivityId() {
        return coordinationActivityId;
    }
}
