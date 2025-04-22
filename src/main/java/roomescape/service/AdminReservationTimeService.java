package roomescape.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.ReservationTime;
import roomescape.repository.ReservationTimeRepository;
import roomescape.service.dto.ReservationTimeInfo;

@Service
@RequiredArgsConstructor
public class AdminReservationTimeService {

    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTime create(ReservationTimeInfo reservationTimeInfo) {
       return reservationTimeRepository.save(reservationTimeInfo.toDomain());
    }

    public List<ReservationTime> getAll() {
        return reservationTimeRepository.getAll();
    }

    public void delete(Long id) {
        ReservationTime reservation = getReservationById(id);
        reservationTimeRepository.remove(reservation);
    }

    private ReservationTime getReservationById(Long reservationId) {
        return reservationTimeRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 시간이 존재하지 않습니다."));
    }
}
