package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoomsSearchService;
import pl.polsl.confrooms.model.ConferenceRoom.Requests.ConferenceRoomSearchRequest;

import java.util.Date;


//CONTROLLER ODPOWIADAJACY ZA ZWRACANIE ODPOWIEDNICH WIDOKOW DLA POSZCZEGOLNYCH ZAPYTAN NA TEMAT WYSZUKIWANIA SAL KONFERENCYJNYCH
@Controller
@RequestMapping("")
@AllArgsConstructor
public class SearchController {
    private ConferenceRoomsSearchService conferenceRoomsSearchService;

    //tutaj trzeba zaimplementowac default date - aktualna date.
    @GetMapping("/ConferenceRooms")
    @PreAuthorize("permitAll()")
    public ModelAndView searchConferenceRooms(@RequestParam(value = "page", defaultValue = "0") int page, ConferenceRoomSearchRequest conferenceRoomSearchRequest) {
//        jesli ilosc miejsc w sali nie zostala przekazana ustawiam wartosc na 0
        if (conferenceRoomSearchRequest.getNumberOfSeats() == null) {
            conferenceRoomSearchRequest.setNumberOfSeats(0);
        }
//        jesli data nie zostala przekazana ustawiam ja na aktualna. Jesli data jest przeszla ustawiam ja na aktualna.
        if (conferenceRoomSearchRequest.getDate() == null) {
            conferenceRoomSearchRequest.setDate(new Date());
        } else if (conferenceRoomSearchRequest.getDate().before(new Date())) {
            conferenceRoomSearchRequest.setDate(new Date());
        }
//        wyszukuje odpowiednie sale konferencyjne.
        ModelAndView response = new ModelAndView("conference_rooms/rooms_list");
        response.addObject(
                "conferenceRooms",
                conferenceRoomsSearchService.searchConferenceRooms(page, conferenceRoomSearchRequest)
        );
        return response;
    }
}
