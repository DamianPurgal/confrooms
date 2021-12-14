package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoom;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoomService;
import pl.polsl.confrooms.model.ConferenceRoom.Requests.ConferenceRoomAddRequest;
import pl.polsl.confrooms.model.ConferenceRoom.Responses.ConferenceRoomAddResponse;
import pl.polsl.confrooms.model.User.User;

import java.util.List;

@Controller
@RequestMapping("/userPanel/ConferenceRooms")
@AllArgsConstructor
public class ConferenceRoomController {

    private ConferenceRoomService conferenceRoomService;

    @GetMapping
    public ModelAndView showConferenceRooms(@RequestParam(value = "page", defaultValue = "0") int page) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/owner_conference_rooms");
        response.addObject("conferenceRooms", conferenceRoomService.getAllConferenceRoomsOfUser((User)loggedUser));
        return response;
    }

    @GetMapping("/addConferenceRoom")
    public String showFormAddConferenceRoom() {
        return "user_panel/add_room";
    }

    @PostMapping("/deleteConferenceRoom")
    public String deleteConferenceRoom(int conferenceRoomId) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        conferenceRoomService.deleteOwnerConferenceRoom((User) loggedUser, (long) conferenceRoomId);
        return "redirect:/userPanel/ConferenceRooms";
    }

    @PostMapping("/addConferenceRoom")
    public ModelAndView addConferenceRoom(ConferenceRoomAddRequest conferenceRoomAddRequest) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ConferenceRoom conferenceRoom = new ConferenceRoom(
                ((User) loggedUser).getId(),
                conferenceRoomAddRequest.getName(),
                conferenceRoomAddRequest.getHomeNumber(),
                conferenceRoomAddRequest.getStreet(),
                conferenceRoomAddRequest.getLedScreen() != null,
                conferenceRoomAddRequest.getAirConditioning() != null,
                conferenceRoomAddRequest.getInternet() != null,
                conferenceRoomAddRequest.getSoundSystem() != null,
                conferenceRoomAddRequest.getPrice()
        );
        ModelAndView response = new ModelAndView("user_panel/add_conference_room_response");
        response.addObject("response", conferenceRoomService.addRoom(conferenceRoom));
        return response;
    }

    //    @GetMapping("/getall")
    //    @ResponseBody
    //    public List<ConferenceRoom> getAllRooms() {
    //        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //        return conferenceRoomService.getAllConferenceRoomsOfUser((User)loggedUser);
    //    }
}
