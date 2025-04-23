package roomescape.service.dto.query;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;

public record ReservationQuery(
        Long id,
        String name,
        LocalDate date,
        ReservationTimeQuery time
) {

    public record ReservationTimeQuery(
            Long id,
            LocalTime startAt
    ) {
    }

    public static ReservationQuery from(Reservation reservation) {
        ReservationTime reservationTime = reservation.getTime();

        return new ReservationQuery(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                new ReservationTimeQuery(reservationTime.getId(), reservationTime.getStartAt())
        );
    }
}
