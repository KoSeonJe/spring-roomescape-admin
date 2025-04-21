package roomescape.infra;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationRepository;
import roomescape.util.DateTimeUtils;

@Repository
@RequiredArgsConstructor
public class ReservationInMemoryJdbcRepository implements ReservationRepository {

    private static final RowMapper<Reservation> ROW_MAPPER = (resultSet, rowNum) -> new Reservation(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            DateTimeUtils.parseToDate(resultSet.getString("date")),
            DateTimeUtils.parseToTime(resultSet.getString("time"))
    );

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Reservation> getAll() {
        String getAllQuery = "SELECT * FROM reservation";
        return jdbcTemplate.query(getAllQuery, ROW_MAPPER);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return null;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {

    }
}
