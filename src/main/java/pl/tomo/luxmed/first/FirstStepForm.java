package pl.tomo.luxmed.first;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
class FirstStepForm {

    @NotBlank
    private String cityId;
    @NotBlank
    private String serviceId;
    private String clinicId;
}
