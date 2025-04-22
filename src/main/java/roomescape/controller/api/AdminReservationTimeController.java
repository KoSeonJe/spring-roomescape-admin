package roomescape.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.controller.api.dto.request.CreateReservationTimeRequest;
import roomescape.controller.api.dto.response.ReservationTimeResponse;
import roomescape.domain.ReservationTime;
import roomescape.domain.ReservationTimeRepository;

@RestController
@RequestMapping("/admin/times")
@RequiredArgsConstructor
public class AdminReservationTimeController {

    private final ReservationTimeRepository reservationTimeRepository;

    @PostMapping
    public ReservationTimeResponse create(@RequestBody CreateReservationTimeRequest request) {
        ReservationTime reservationTime = request.toDomain();
        ReservationTime createdReservation = reservationTimeRepository.create(reservationTime);
        return ReservationTimeResponse.from(createdReservation);
    }
}
