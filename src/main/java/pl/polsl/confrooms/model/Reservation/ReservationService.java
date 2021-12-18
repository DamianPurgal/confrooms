package pl.polsl.confrooms.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoomService;
import pl.polsl.confrooms.repository.ReservationRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public String getReservedDatesOfConferenceRoom(Long id) {
        List<Reservation> listOfConferenceRooms = reservationRepository.findByConferenceRoomId(id);
        String reservedDatesOfConferenceRoom = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Reservation reservation : listOfConferenceRooms) {
            reservedDatesOfConferenceRoom += "," + df.format(reservation.getDate());
        }
        return reservedDatesOfConferenceRoom;
    }

    public boolean isConferenceRoomReservationExists(Long conferenceRoomId, Date date) {
        return reservationRepository.existsByConferenceRoomIdAndDate(conferenceRoomId, date);
    }

    public ReservationAddResponse addConfrerenceRoomReservation(Reservation reservation) {
//        Generuje date z zerowym czasem
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = new Date();
        try {
            todayDate = formatter.parse(formatter.format(todayDate));
        } catch (ParseException e) {
            return new ReservationAddResponse(false, "Błąd!", "Nastąpił błąd wewnętrzny serwera!");
        }
//        sprawdzam czy data nie jest przeszła, sprawdzam czy rezerwacja istnieje
        if (reservation.getDate().before(todayDate)) {
            return new ReservationAddResponse(false, "Błąd!", "Nie mozesz podać daty która miała już miejsce!");
        }
        if (reservationRepository.existsByConferenceRoomIdAndDate(reservation.getConferenceRoomId(), reservation.getDate())) {
            return new ReservationAddResponse(false, "Błąd!", "Podana sala nie istnieje lub data jest zajęta!");
        }
//        nie sprawdzam czy istnieje taki uzytkownik
        reservationRepository.save(reservation);
        return new ReservationAddResponse(true, "Sukces", "Pomyślnie zarezerwowano salę");
    }
}
