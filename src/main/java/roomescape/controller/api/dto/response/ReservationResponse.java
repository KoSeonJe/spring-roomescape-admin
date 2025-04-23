package roomescape.controller.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.service.dto.query.ReservationQuery;

public record ReservationResponse(
        Long id,
        String name,
        LocalDate date,
        ReservationTimeResponse time
) {

    record ReservationTimeResponse(
            Long id,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
            LocalTime startAt
    ) {
    }

    public static ReservationResponse from(ReservationQuery query) {
        return new ReservationResponse(
                query.id(),
                query.name(),
                query.date(),
                new ReservationTimeResponse(query.time().id(), query.time().startAt())
                );
    }
}
