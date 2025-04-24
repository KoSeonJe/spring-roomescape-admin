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
    public ReservationTime save(ReservationTime reservationTime) {
        String insertQuery = "INSERT INTO reservation_time (start_at) VALUES (?)";
        return reservationTimeDao.insertAndGet(insertQuery, reservationTime);
    }

    @Override
    public List<ReservationTime> getAll() {
        String selectQuery = "SELECT * FROM reservation_time";
        return reservationTimeDao.getAllQuery(selectQuery);
    }

    @Override
    public Optional<ReservationTime> findById(Long id) {
        String selectQuery = "SELECT * FROM reservation_time WHERE id = ?";
        return reservationTimeDao.getQuery(selectQuery, id);
    }

    @Override
    public void remove(ReservationTime reservationTime) {
        String deleteQuery = "DELETE FROM reservation_time WHERE id = ?";
        reservationTimeDao.update(deleteQuery, reservationTime.getId());
    }
}
