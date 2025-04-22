package roomescape.infra;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationRepository;

@Repository
@RequiredArgsConstructor
public class ReservationH2Repository implements ReservationRepository {

    private static final RowMapper<Reservation> ROW_MAPPER = (resultSet, rowNum) -> new Reservation(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getDate("date").toLocalDate(),
            resultSet.getTime("time").toLocalTime()
    );

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Reservation> getAll() {
        String selectAllQuery = "SELECT * FROM reservation";
        return jdbcTemplate.query(selectAllQuery, ROW_MAPPER);
    }

    @Override
    public Reservation save(Reservation reservation) {
        String insertQuery = "INSERT INTO reservation (name, date, time) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"id"});
            ps.setString(1, reservation.getName());
            ps.setString(2, reservation.getDate().toString());
            ps.setString(3, reservation.getTime().toString());
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();

        return new Reservation(id, reservation.getName(), reservation.getDate(), reservation.getTime());
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        String selectQuery = "SELECT * FROM reservation WHERE id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(selectQuery, ROW_MAPPER, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void remove(Reservation reservation) {
        String deleteQuery = "DELETE FROM reservation WHERE id = ?";
        jdbcTemplate.update(deleteQuery, reservation.getId());
    }
}
