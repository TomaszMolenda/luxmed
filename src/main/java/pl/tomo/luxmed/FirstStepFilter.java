package pl.tomo.luxmed;

import lombok.Data;

import java.util.List;

@Data
class FirstStepFilter {

    private final List<Clinic> clinics;
    private final List<City> cities;
    private final List<MediService> mediServices;
}
