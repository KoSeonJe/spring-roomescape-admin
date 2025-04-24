package roomescape.repository.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationTime;

@Component
@RequiredArgsConstructor
public class ReservationDao {

    private static final RowMapper<Reservation> ROW_MAPPER = (resultSet, rowNum) -> new Reservation(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getDate("date").toLocalDate(),
            new ReservationTime(
                    resultSet.getLong("reservation.time_id"),
                    resultSet.getTime("reservation_time.start_at").toLocalTime()
            )
    );

    private final JdbcTemplate jdbcTemplate;

    public List<Reservation> getAllQuery() {
        String selectAllQuery = """
                SELECT *
                FROM reservation
                INNER JOIN reservation_time
                ON reservation.time_id = reservation_time.id
                """;
        return jdbcTemplate.query(selectAllQuery, ROW_MAPPER);
    }

    public Reservation insertAndGet(Reservation reservation) {
        String insertQuery = "INSERT INTO reservation (name, date, time_id) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"id"});
            ps.setString(1, reservation.getName());
            ps.setString(2, reservation.getDate().toString());
            ps.setLong(3, reservation.getTime().getId());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();

        return new Reservation(id, reservation.getName(), reservation.getDate(), reservation.getTime());
    }

    public Optional<Reservation> getQuery(Long id) {
        String selectQuery = """
                SELECT *
                FROM reservation
                INNER JOIN reservation_time
                ON reservation.time_id = reservation_time.id
                WHERE reservation.id = ?
                """;
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(selectQuery, ROW_MAPPER, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        String deleteQuery = "DELETE FROM reservation WHERE id = ?";
        jdbcTemplate.update(deleteQuery, id);
    }
}
