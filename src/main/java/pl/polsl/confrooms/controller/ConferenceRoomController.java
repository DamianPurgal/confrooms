package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoom;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoomService;
import pl.polsl.confrooms.model.ConferenceRoom.Requests.ConferenceRoomAddRequest;
import pl.polsl.confrooms.model.Exceptions.NotFoundException;
import pl.polsl.confrooms.model.User.User;

//CONTROLLER ODPOWIADAJACY ZA ZWRACANIE ODPOWIEDNICH WIDOKOW DLA POSZCZEGOLNYCH ZAPYTAN NA TEMAT SAL KONFERENCYJNYCH
@Controller
@RequestMapping("")
@AllArgsConstructor
public class ConferenceRoomController {

    private ConferenceRoomService conferenceRoomService;

    @GetMapping("/userPanel/ConferenceRooms")
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public ModelAndView showConferenceRooms() {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/owner_conference_rooms");
        response.addObject("conferenceRooms", conferenceRoomService.getAllConferenceRoomsOfUser((User) loggedUser));
        return response;
    }

    @GetMapping("/userPanel/ConferenceRooms/addConferenceRoom")
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public String showFormAddConferenceRoom() {
        return "user_panel/add_room";
    }

    @PostMapping("/userPanel/ConferenceRooms/deleteConferenceRoom")
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public ModelAndView deleteConferenceRoom(@RequestParam(value = "conferenceRoomId") int conferenceRoomId) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/delete_conference_room_response");
        response.addObject("response", conferenceRoomService.deleteOwnerConferenceRoom((User) loggedUser, (long) conferenceRoomId));
        return response;
    }

    @PostMapping("/userPanel/ConferenceRooms/addConferenceRoom")
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
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
    @PreAuthorize("permitAll()")
    public ModelAndView getDetailedConferenceRoomView(@RequestParam(value = "id", defaultValue = "-1") Long id) {
        try {
            ModelAndView response = new ModelAndView("conference_rooms/conference_room_detailed");
            response.addObject("conferenceRoom", conferenceRoomService.getConferenceRoom(id));
            return response;

        } catch (NotFoundException e) {
            ModelAndView response = new ModelAndView("conference_rooms/conference_room_details_failed_response");
            response.addObject("errorMessage", "Sala nie istnieje.");
            return response;
        }

    }


}
