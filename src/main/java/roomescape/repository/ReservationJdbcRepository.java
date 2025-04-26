package roomescape.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.repository.dao.ReservationH2Dao;

@Repository
@RequiredArgsConstructor
public class ReservationJdbcRepository implements ReservationRepository {

    private final ReservationH2Dao reservationH2Dao;

    @Override
    public List<Reservation> getAll() {
        return reservationH2Dao.getAllQuery();
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationH2Dao.insertAndGet(reservation);
    }

    @Override
    public Optional<Reservation> findById(Long reservationId) {
        return reservationH2Dao.getQuery(reservationId);
    }

    @Override
    public void remove(Reservation reservation) {
        reservationH2Dao.deleteById(reservation.id());
    }
}
