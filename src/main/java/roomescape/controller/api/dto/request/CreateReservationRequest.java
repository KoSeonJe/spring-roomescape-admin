package roomescape.controller.api.dto.request;

import java.time.LocalDate;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;

public record CreateReservationRequest(
        String name,
        LocalDate date,
        Long timeId
) {
    public CreateReservationRequest {
        if (name == null) {
            throw new IllegalArgumentException("이름은 필수값입니다.");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 비어 있을 수 없습니다.");
        }
        if (date == null) {
            throw new IllegalArgumentException("날짜는 필수값입니다.");
        }
        if (timeId == null) {
            throw new IllegalArgumentException("timeId는 필수값입니다.");
        }
    }

    public Reservation toDomain(ReservationTime reservationTime) {
        return new Reservation(name, date, reservationTime);
    }
}
