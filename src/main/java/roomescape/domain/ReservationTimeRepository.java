package roomescape.domain;

import java.util.List;

public interface ReservationTimeRepository {

    ReservationTime create(ReservationTime reservationTime);

    List<ReservationTime> getAll();
}
