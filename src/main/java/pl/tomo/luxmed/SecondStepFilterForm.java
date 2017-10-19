package pl.tomo.luxmed;

import lombok.Data;

@Data
public class SecondStepFilterForm extends FirstStepFilterForm {

    private String isFromStartPage;
    private String payersCount;
    private String searchFirstFree;
    private String requestVerificationToken;
    private String timeOption;
    private String toDate;
}
