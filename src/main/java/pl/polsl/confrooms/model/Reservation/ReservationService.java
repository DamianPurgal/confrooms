package pl.polsl.confrooms.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

}
