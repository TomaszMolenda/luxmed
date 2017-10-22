package pl.tomo.luxmed.summary;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class SummaryForm {

    private String cityId;
    private String serviceId;
    private String clinicId;
    private String doctorId;
    @NotEmpty
    private String payerId;
}
