package roomescape.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.repository.dao.ReservationDao;

@Repository
@RequiredArgsConstructor
public class ReservationJdbcRepository implements ReservationRepository {

    private final ReservationDao reservationDao;

    @Override
    public List<Reservation> getAll() {
        return reservationDao.getAllQuery();
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationDao.insertAndGet(reservation);
    }

    @Override
    public Optional<Reservation> findById(Long reservationId) {
        return reservationDao.getQuery(reservationId);
    }

    @Override
    public void remove(Reservation reservation) {
        reservationDao.deleteById(reservation.getId());
    }
}
