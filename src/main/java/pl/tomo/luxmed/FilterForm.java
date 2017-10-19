package pl.tomo.luxmed;

import lombok.Data;

import java.time.LocalDate;

@Data
class FilterForm {

    private String cityId;
    private String  postId;
    private String serviceId;
    private LocalDate dateFrom;
    private LocalDate toDate;
    private String doctorId;
    private String payerId;
    private String payersCount;
    private String isFromStartPage;
    private String searchFirstFree;
    private String requestVerificationToken;
    private String timeOption;
    private String clinicId;
}
