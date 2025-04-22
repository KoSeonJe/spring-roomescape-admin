package roomescape.service.dto;

import java.time.LocalDate;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;

public record ReservationInfo(
        String name,
        LocalDate date,
        Long timeId
) {

    public Reservation toDomain(ReservationTime reservationTime) {
        return new Reservation(name, date, reservationTime);
    }
}
