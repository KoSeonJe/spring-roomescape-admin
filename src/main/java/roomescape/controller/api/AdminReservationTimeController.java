package roomescape.controller.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReservationTimeResponse create(@RequestBody CreateReservationTimeRequest request) {
        ReservationTime reservationTime = request.toDomain();
        ReservationTime createdReservation = reservationTimeRepository.create(reservationTime);
        return ReservationTimeResponse.from(createdReservation);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ReservationTimeResponse> getAll() {
        return reservationTimeRepository.getAll().stream()
                .map(ReservationTimeResponse::from)
                .toList();
    }
}
