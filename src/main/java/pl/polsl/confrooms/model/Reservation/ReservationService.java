package pl.polsl.confrooms.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.repository.ReservationRepository;

import java.util.Date;
import java.util.List;

//SERVICE (MODEL W MVC) ODPOWIEDZIALNY ZA REZERWACJE SALI KONFERENCYJNEJ W DANYM DNIU
@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> getReservationsByDate(Date date) {
        return reservationRepository.findByDate(date);
    }

    public List<Reservation> isConferenceRoomReservationExists(Long conferenceRoomId) {
        return reservationRepository.findByConferenceRoomId(conferenceRoomId);
    }
}
