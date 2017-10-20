package pl.tomo.luxmed.first;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import pl.tomo.luxmed.storage.SimpleData;

@Data
class FirstStepForm implements SimpleData {

    @NotBlank
    private String cityId;
    @NotBlank
    private String serviceId;
    private String clinicId;
}
