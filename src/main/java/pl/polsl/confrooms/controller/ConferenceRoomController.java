package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoom;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoomService;
import pl.polsl.confrooms.model.ConferenceRoom.Requests.ConferenceRoomAddRequest;
import pl.polsl.confrooms.model.Exceptions.NotFoundException;
import pl.polsl.confrooms.model.Reservation.Reservation;
import pl.polsl.confrooms.model.Reservation.ReservationService;
import pl.polsl.confrooms.model.User.User;
import pl.polsl.confrooms.model.User.UserService;

import java.util.Date;


//CONTROLLER ODPOWIADAJACY ZA ZWRACANIE ODPOWIEDNICH WIDOKOW DLA POSZCZEGOLNYCH ZAPYTAN NA TEMAT SAL KONFERENCYJNYCH
@Controller
@RequestMapping("")
@AllArgsConstructor
public class ConferenceRoomController {

    private ConferenceRoomService conferenceRoomService;

    @GetMapping("/userPanel/ConferenceRooms")
    public ModelAndView showConferenceRooms(@RequestParam(value = "page", defaultValue = "0") int page) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/owner_conference_rooms");
        response.addObject("conferenceRooms", conferenceRoomService.getAllConferenceRoomsOfUser((User) loggedUser));
        return response;
    }

    @GetMapping("/userPanel/ConferenceRooms/addConferenceRoom")
    public String showFormAddConferenceRoom() {
        return "user_panel/add_room";
    }

    @PostMapping("/userPanel/ConferenceRooms/deleteConferenceRoom")
    public ModelAndView deleteConferenceRoom(int conferenceRoomId) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/delete_conference_room_response");
        response.addObject("response", conferenceRoomService.deleteOwnerConferenceRoom((User) loggedUser, (long) conferenceRoomId));
        return response;
    }

    @PostMapping("/userPanel/ConferenceRooms/addConferenceRoom")
    public ModelAndView addConferenceRoom(ConferenceRoomAddRequest conferenceRoomAddRequest) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ConferenceRoom conferenceRoom = new ConferenceRoom(
                ((User) loggedUser).getId(),
                conferenceRoomAddRequest.getNumberOfSeats(),
                conferenceRoomAddRequest.getName(),
                conferenceRoomAddRequest.getDescription(),
                conferenceRoomAddRequest.getHomeNumber(),
                conferenceRoomAddRequest.getStreet(),
                conferenceRoomAddRequest.getLedScreen() != null,
                conferenceRoomAddRequest.getAirConditioning() != null,
                conferenceRoomAddRequest.getInternet() != null,
                conferenceRoomAddRequest.getSoundSystem() != null,
                conferenceRoomAddRequest.getPrice()
        );
        ModelAndView response = new ModelAndView("user_panel/add_conference_room_response");
        response.addObject("response", conferenceRoomService.addConferenceRoom(conferenceRoom));
        return response;
    }

    @GetMapping("/ConferenceRoom")
    public ModelAndView getDeailedConferenceRoomView(@RequestParam(value = "id", defaultValue = "-1") Long id, @DateTimeFormat(pattern = "yyyy-MM-dd")Date date) {
//        jesli data nie zostala przekazana ustawiam ja na aktualna. Jesli data jest przeszla ustawiam ja na aktualna.
        if (date == null) {
            date = new Date();
        } else if (date.before(new Date())) {
            date = new Date();
        }
        try{
            ModelAndView response = new ModelAndView("conference_rooms/conference_room_detailed");
            response.addObject("conferenceRoom", conferenceRoomService.getConferenceRoom(id));
            response.addObject("date", new Date());
            return response;

        }catch(NotFoundException e){
            ModelAndView response = new ModelAndView("conference_rooms/conference_room_details_failed_response");
            response.addObject("errorMessage", "Sala nie istnieje.");
            return response;
        }

    }


}
