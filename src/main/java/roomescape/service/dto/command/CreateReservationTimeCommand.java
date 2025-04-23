package roomescape.service.dto.command;

import java.time.LocalTime;
import roomescape.domain.ReservationTime;

public record CreateReservationTimeCommand(
        LocalTime startAt
) {

    public ReservationTime toDomain() {
        return new ReservationTime(startAt);
    }
}
