package roomescape.service.dto.query;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.domain.Reservation;

public record ReservationQuery(
        Long id,
        String name,
        LocalDate date,
        Long timeId,
        LocalTime startAt
) {

    public static ReservationQuery from(Reservation reservation) {
        return new ReservationQuery(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                reservation.getTime().getId(),
                reservation.getTime().getStartAt()
        );
    }
}
