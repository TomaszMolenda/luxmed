package pl.tomo.luxmed;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FirstStepFilterForm {

    private String cityId;
    private String  postId;
    private String serviceId;
    private LocalDate dateFrom;
    private String doctorId;
    private String payerId;
    private String payersCount;
}
