package roomescape.controller.api.dto.request;

import java.time.LocalTime;
import roomescape.service.dto.ReservationTimeInfo;

public record CreateReservationTimeRequest(
        LocalTime startAt
) {

    public ReservationTimeInfo toDomainInfo() {
        return new ReservationTimeInfo(startAt);
    }
}
