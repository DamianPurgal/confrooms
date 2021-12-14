package pl.polsl.confrooms.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoom;

import java.util.List;


public interface ConferenceRoomRepository extends CrudRepository<ConferenceRoom, Long> {
    List<ConferenceRoom> findByOwnerId(Long ownerId);
}
