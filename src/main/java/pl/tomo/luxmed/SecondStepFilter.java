package pl.tomo.luxmed;

import lombok.Data;

import java.util.List;

@Data
public class SecondStepFilter {

    private final List<Doctor> doctors;
    private final List<Payer> payers;
    private final String requestVerificationToken;
}
