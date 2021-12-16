package pl.polsl.confrooms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoom;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;


public interface ConferenceRoomRepository extends CrudRepository<ConferenceRoom, Long> {
    List<ConferenceRoom> findByOwnerId(Long ownerId);

    Page<ConferenceRoom> findByLedScreenGreaterThanEqualAndAirConditioningGreaterThanEqualAndInternetGreaterThanEqualAndSoundSystemGreaterThanEqualAndNumberOfSeatsGreaterThanEqualAndIdNotIn(Boolean ledScreen, Boolean airConditioning, Boolean internet, Boolean soundSystem, Integer numberOfSeats, Set<Long> reservedConferenceRoomsConferenceRoomId, Pageable pageable);
}
