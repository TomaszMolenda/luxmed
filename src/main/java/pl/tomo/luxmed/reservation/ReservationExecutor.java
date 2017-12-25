package pl.tomo.luxmed.reservation;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ReservationExecutor {

    private final ReservationStatusUpdater reservationStatusUpdater;
    private final ReservationChecker reservationChecker;
    private final ReservationRemover reservationRemover;
    private final ReservationConfirmer reservationConfirmer;

    @Autowired
    ReservationExecutor(ReservationStatusUpdater reservationStatusUpdater, ReservationChecker reservationChecker, ReservationRemover reservationRemover, ReservationConfirmer reservationConfirmer) {
        this.reservationStatusUpdater = reservationStatusUpdater;
        this.reservationChecker = reservationChecker;
        this.reservationRemover = reservationRemover;
        this.reservationConfirmer = reservationConfirmer;
    }

    void reserve(Reservation reservation) {

        final ReservationStatus reservationStatus = reservationChecker.checkIfIsReservation(reservation);

        if (reservationStatus.isExist()) {

            reservationRemover.remove(reservationStatus);
        }

        Document document = reservationConfirmer.confirm(reservation);

        reservationStatusUpdater.update(reservation, document);
    }
}
