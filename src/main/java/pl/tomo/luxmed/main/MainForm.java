package pl.tomo.luxmed.main;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
class MainForm {

    @NotBlank
    private String cityId;
    @NotBlank
    private String serviceId;
    private String clinicId;
}
