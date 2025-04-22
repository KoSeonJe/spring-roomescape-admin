package roomescape.service.dto;

import java.time.LocalTime;
import roomescape.domain.ReservationTime;

public record ReservationTimeInfo(
        LocalTime startAt
) {

    public ReservationTime toDomain() {
        return new ReservationTime(startAt);
    }
}
