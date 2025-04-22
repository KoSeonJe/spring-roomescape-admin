package roomescape.controller.api.dto.request;

import java.time.LocalTime;
import roomescape.domain.ReservationTime;

public record CreateReservationTimeRequest(
        LocalTime startAt
) {

    public ReservationTime toDomain() {
        return new ReservationTime(startAt);
    }
}
