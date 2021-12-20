package pl.polsl.confrooms.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoom;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoomService;
import pl.polsl.confrooms.model.Reservation.Responses.ReservationAddResponse;
import pl.polsl.confrooms.model.Reservation.Responses.ReservationDeleteResponse;
import pl.polsl.confrooms.model.Reservation.Responses.ReservationUserPanelResponse;
import pl.polsl.confrooms.model.User.User;
import pl.polsl.confrooms.repository.ConferenceRoomRepository;
import pl.polsl.confrooms.repository.ReservationRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//SERVICE (MODEL W MVC) ODPOWIEDZIALNY ZA REZERWACJE SALI KONFERENCYJNEJ W DANYM DNIU
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    public List<Reservation> getReservationsByDate(Date date) {
        return reservationRepository.findByDate(date);
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
            return new ReservationAddResponse(
                    false,
                    "Błąd!",
                    "Nastąpił błąd wewnętrzny serwera!"
            );
        }
//        sprawdzam czy data nie jest przeszła, sprawdzam czy rezerwacja istnieje
        if (reservation.getDate().before(todayDate)) {
            return new ReservationAddResponse(
                    false,
                    "Błąd!",
                    "Nie mozesz podać daty która miała już miejsce!"
            );
        }
        if (reservationRepository.existsByConferenceRoomIdAndDate(reservation.getConferenceRoomId(), reservation.getDate())) {
            return new ReservationAddResponse(
                    false,
                    "Błąd!",
                    "Podana sala nie istnieje lub data jest zajęta!"
            );
        }
//        nie sprawdzam czy istnieje taki uzytkownik
        reservationRepository.save(reservation);
        return new ReservationAddResponse(
                true,
                "Sukces",
                "Pomyślnie zarezerwowano salę"
        );
    }

    public List<ReservationUserPanelResponse> getUserReservations(Long tenantId) {
        List<Reservation> reservations = reservationRepository.findByTenantId(tenantId);
        List<ReservationUserPanelResponse> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            Optional<ConferenceRoom> conferenceRoom = conferenceRoomRepository.findById(reservation.getConferenceRoomId());
            if (conferenceRoom.isPresent()) {
                result.add(new ReservationUserPanelResponse(
                        conferenceRoom.get(),
                        reservation.getDate(),
                        reservation.getId()
                ));
            }
        }
        return result;
    }

    public ReservationDeleteResponse deleteUserReservation(Long reservationId, User user) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isPresent()) {
            if (reservation.get().getTenantId() == user.getId()) {
                reservationRepository.delete(reservation.get());
                return new ReservationDeleteResponse(
                        true,
                        "Sukces",
                        "Usunięto rezerwację."
                );
            } else {
                return new ReservationDeleteResponse(
                        false,
                        "Błąd!",
                        "Ta rezerwacja nie została wykonana przez Ciebie!"
                );
            }
        } else {
            return new ReservationDeleteResponse(
                    false,
                    "Błąd!",
                    "Taka rezerwacja nie istnieje."
            );
        }
    }
}
