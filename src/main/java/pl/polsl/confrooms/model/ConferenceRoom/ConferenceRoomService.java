package pl.polsl.confrooms.model.ConferenceRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.model.User.Responses.UserRegistrationResponse;
import pl.polsl.confrooms.model.User.User;
import pl.polsl.confrooms.repository.ConferenceRoomRepository;

import java.util.List;

@Service
public class ConferenceRoomService {

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    public UserRegistrationResponse addRoom(ConferenceRoom conferenceRoom) {
        conferenceRoomRepository.save(conferenceRoom);
        return new UserRegistrationResponse(true, "Sukces!", "Dodano nową sale konferencyjną!");
    }
    public List<ConferenceRoom> getAllConferenceRoomsOfUser(User user) {
        return conferenceRoomRepository.findByOwnerId(user.getId());
    }
    public String deleteOwnerConferenceRoom(User user, Long conferenceRoomId) {
        List<ConferenceRoom> ownerConferenceRooms = getAllConferenceRoomsOfUser(user);
        for(ConferenceRoom room: ownerConferenceRooms) {
            if(room.getOwnerId() == user.getId() && room.getId() == conferenceRoomId) {
                conferenceRoomRepository.delete(room);
                return "Sala zostal usunieta";
            }
        }
        return "Uzytkownik nie ma autoryzacji do usuniecia tej sali";
    }
}
