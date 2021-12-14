package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoomService;

@Controller
@RequestMapping("/userPanel/reservedRooms")
@AllArgsConstructor
public class RoomsController {

    private ConferenceRoomService conferenceRoomService;

    @GetMapping
    public String reservedRooms(){
        return "user_panel/reserved_rooms";
    }
    @GetMapping("/addRoom")
    public String formAddRoom(){
        return "user_panel/add_room";
    }
}
