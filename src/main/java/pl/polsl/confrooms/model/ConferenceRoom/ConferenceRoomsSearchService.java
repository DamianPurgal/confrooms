package pl.polsl.confrooms.model.ConferenceRoom;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.model.ConferenceRoom.Requests.ConferenceRoomSearchRequest;
import pl.polsl.confrooms.model.Reservation.Reservation;
import pl.polsl.confrooms.model.Reservation.ReservationService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//SERVICE (MODEL W MVC) ODPOWIEDZIALNY ZA WYSZUKIWANIE SAL KONFERENYJNYCH
@Service
@AllArgsConstructor
public class ConferenceRoomsSearchService {

    private ReservationService reservationService;
    private ConferenceRoomService conferenceRoomService;

    public Page<ConferenceRoom> searchConferenceRooms(int page, ConferenceRoomSearchRequest conferenceRoomSearchRequest) {
//        wyszukanie rezerwacji sal w danym dniu
        List<Reservation> reservations = reservationService.getReservationsByDate(conferenceRoomSearchRequest.getDate());
        Set<Long> reservationConferenceRoomIds = new HashSet<>();
        reservationConferenceRoomIds.add(Long.valueOf(-1));
//        stworzenie set'u z ID sal zarezerwowanych w danym dniu
        for (Reservation res : reservations) {
            reservationConferenceRoomIds.add(res.getConferenceRoomId());
        }
//        zwrocenie wyszukanych sal konferenyjnych.
        return conferenceRoomService.getSearchedConferenceRooms(
                page,
                conferenceRoomSearchRequest.getLedScreen() != null,
                conferenceRoomSearchRequest.getAirConditioning() != null,
                conferenceRoomSearchRequest.getInternet() != null,
                conferenceRoomSearchRequest.getSoundSystem() != null,
                conferenceRoomSearchRequest.getNumberOfSeats(),
                reservationConferenceRoomIds
        );
    }
}
