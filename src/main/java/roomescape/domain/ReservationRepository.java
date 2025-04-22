package roomescape.domain;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    List<Reservation> getAll();

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(Long id);

    void remove(Reservation reservation);
}
