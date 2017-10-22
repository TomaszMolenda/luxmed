package pl.tomo.luxmed.reservation;

import lombok.Data;

@Data
class ReservationStatus {

    private final boolean exist;
    private final String id;
}
