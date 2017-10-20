package pl.tomo.luxmed.second;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
class SecondStepForm {

    private String cityId;
    private String serviceId;
    private String clinicId;
    private String doctorId;
    @NotEmpty
    private String payerId;
}
