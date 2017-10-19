package pl.tomo.luxmed;

import lombok.Data;

@Data
class Reservation {

    private final String date;
    private final String hour;
    private final String termId;
}
