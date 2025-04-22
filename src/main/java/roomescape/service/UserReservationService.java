package roomescape.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.Reservation;
import roomescape.repository.ReservationRepository;
import roomescape.domain.ReservationTime;
import roomescape.repository.ReservationTimeRepository;
import roomescape.service.dto.ReservationInfo;

@Service
@RequiredArgsConstructor
public class UserReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationTimeRepository reservationTimeRepository;


    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Reservation create(ReservationInfo reservationInfo) {
        ReservationTime reservationTime = getReservationTime(reservationInfo.timeId());
        Reservation reservation = reservationInfo.toDomain(reservationTime);
        return reservationRepository.save(reservation);
    }

    public void delete(Long id) {
        Reservation target = getReservation(id);
        reservationRepository.remove(target);
    }

    private ReservationTime getReservationTime(Long timeId) {
        return reservationTimeRepository.findById(timeId)
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 시간이 존재하지 않습니다."));
    }

    private Reservation getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 예약이 존재하지 않습니다."));
    }
}
