package pl.polsl.confrooms.model.ConferenceRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.model.User.UserRegistrationResponse;
import pl.polsl.confrooms.repository.AccountRepository;
import pl.polsl.confrooms.repository.ConferenceRoomRepository;

@Service
public class ConferenceRoomService {

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    public UserRegistrationResponse addRoom(ConferenceRoom room) {
        conferenceRoomRepository.save(room);
        return new UserRegistrationResponse(true, "Sukces!", "Dodano konto użytkownika");
    }

}
