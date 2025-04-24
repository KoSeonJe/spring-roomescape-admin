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
        String selectAllQuery = """
                SELECT *
                FROM reservation
                INNER JOIN reservation_time
                ON reservation.time_id = reservation_time.id
                """;
        return reservationDao.getAllQuery(selectAllQuery);
    }

    @Override
    public Reservation save(Reservation reservation) {
        String insertQuery = "INSERT INTO reservation (name, date, time_id) VALUES (?, ?, ?)";
        return reservationDao.insertAndGet(insertQuery, reservation);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        String selectQuery = """
                SELECT *
                FROM reservation
                INNER JOIN reservation_time
                ON reservation.time_id = reservation_time.id
                WHERE id = ?
                """;
        return reservationDao.getQuery(selectQuery, id);
    }

    @Override
    public void remove(Reservation reservation) {
        String deleteQuery = "DELETE FROM reservation WHERE id = ?";
        reservationDao.update(deleteQuery, reservation.getId());
    }
}
