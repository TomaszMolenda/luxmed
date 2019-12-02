package pl.tomo.luxmed.summary;

import lombok.Data;
import pl.tomo.luxmed.service.FilterForm;

import java.time.LocalDate;

@Data
class FilterFormWithAllData implements FilterForm {

    private String cityId;
    private String clinicId;
    private String serviceId;
    private String payerId;
    private String coordinationActivityId;
}
