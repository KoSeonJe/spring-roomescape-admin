package roomescape.domain;

import java.util.List;
import java.util.Optional;

public interface ReservationTimeRepository {

    ReservationTime create(ReservationTime reservationTime);

    List<ReservationTime> getAll();

    Optional<ReservationTime> findById(Long id);

    void remove(ReservationTime reservation);
}
