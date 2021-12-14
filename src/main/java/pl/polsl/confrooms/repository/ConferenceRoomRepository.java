package pl.polsl.confrooms.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoom;

public interface ConferenceRoomRepository extends CrudRepository<ConferenceRoom, Long> {
}
