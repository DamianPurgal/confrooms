package pl.polsl.confrooms.model.ConferenceRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.model.ConferenceRoom.Responses.ConferenceRoomDeleteResponse;
import pl.polsl.confrooms.model.Exceptions.NotFoundException;
import pl.polsl.confrooms.model.User.Responses.UserRegistrationResponse;
import pl.polsl.confrooms.model.User.User;
import pl.polsl.confrooms.repository.ConferenceRoomRepository;
import pl.polsl.confrooms.repository.ReservationRepository;

import java.util.List;
import java.util.Set;

//SERVICE (MODEL W MVC) ODPOWIEDZIALNY ZA OBSLUGE ZAPYTAN NA TEMAT SAL KONFERENCYJNYCH
@Service
public class ConferenceRoomService {

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public UserRegistrationResponse addConferenceRoom(ConferenceRoom conferenceRoom) {
        if (conferenceRoom.getNumberOfSeats() <= 0) {
            return new UserRegistrationResponse(
                    false,
                    "Błąd!",
                    "Liczba dostępnych miejsc w sali konferencyjnej musi być większa od 0!"
            );
        }
        if (conferenceRoom.getPrice() <= 0) {
            return new UserRegistrationResponse(
                    false,
                    "Błąd!",
                    "Cena wynajmu sali konferencyjnej musi być większa od 0!"
            );
        }
        conferenceRoomRepository.save(conferenceRoom);
        return new UserRegistrationResponse(
                true,
                "Sukces!",
                "Dodano nową sale konferencyjną!"
        );
    }

    public List<ConferenceRoom> getAllConferenceRoomsOfUser(User user) {
        return conferenceRoomRepository.findByOwnerId(user.getId());
    }

    public ConferenceRoomDeleteResponse deleteOwnerConferenceRoom(User user, Long conferenceRoomId) {
        if (!reservationRepository.findByConferenceRoomId(conferenceRoomId).isEmpty()) {
            return new ConferenceRoomDeleteResponse(
                    "Błąd!",
                    "Ta sala została już zarezerwowana przez użytkownika. Możesz usunąć tylko niezarezerwowane sale."
            );
        }
        List<ConferenceRoom> ownerConferenceRooms = getAllConferenceRoomsOfUser(user);
        for (ConferenceRoom room : ownerConferenceRooms) {
            if (room.getOwnerId() == user.getId() && room.getId() == conferenceRoomId) {
                conferenceRoomRepository.delete(room);
                return new ConferenceRoomDeleteResponse(
                        "Sukces!",
                        "Sala została pomyślnie usunięta."
                );
            }
        }
        return new ConferenceRoomDeleteResponse(
                "Błąd!",
                "Uzytkownik nie ma autoryzacji do usuniecia tej sali."
        );
    }

    private final int CONF_ROOMS_PER_PAGE = 9;

    public Page<ConferenceRoom> getSearchedConferenceRooms(int page, Boolean ledScreen, Boolean airConditioning, Boolean internet, Boolean soundSystem, Integer numberOfSeats, Set<Long> reservedConferenceRoomsConferenceRoomId) {
        return conferenceRoomRepository.findByLedScreenGreaterThanEqualAndAirConditioningGreaterThanEqualAndInternetGreaterThanEqualAndSoundSystemGreaterThanEqualAndNumberOfSeatsGreaterThanEqualAndIdNotIn(
                ledScreen,
                airConditioning,
                internet,
                soundSystem,
                numberOfSeats,
                reservedConferenceRoomsConferenceRoomId,
                PageRequest.of(page, CONF_ROOMS_PER_PAGE)
        );
    }

    public ConferenceRoom getConferenceRoom(Long id) throws NotFoundException {
        return conferenceRoomRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Nie znaleziono sali konferencyjnej o podanym id")
                );
    }
}
