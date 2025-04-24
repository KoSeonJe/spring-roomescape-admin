package roomescape.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import roomescape.domain.ReservationTime;
import roomescape.repository.dao.ReservationTimeDao;

@Repository
@RequiredArgsConstructor
public class ReservationTimeJdbcRepository implements ReservationTimeRepository {

    private final ReservationTimeDao reservationTimeDao;

    @Override
    public List<ReservationTime> getAll() {
        return reservationTimeDao.getAllQuery();
    }

    @Override
    public ReservationTime save(ReservationTime reservationTime) {
        return reservationTimeDao.insertAndGet(reservationTime);
    }

    @Override
    public Optional<ReservationTime> findById(Long id) {
        return reservationTimeDao.getQuery(id);
    }

    @Override
    public void remove(ReservationTime reservationTime) {
        reservationTimeDao.deleteById(reservationTime.getId());
    }
}
