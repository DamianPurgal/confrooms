package pl.polsl.confrooms.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polsl.confrooms.model.Reservation.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByDate(Date date);

    List<Reservation> findByConferenceRoomId(Long conferenceRoomId);

    List<Reservation> findByTenantId(Long tenantId);

    boolean existsByConferenceRoomIdAndDate(Long conferenceRoomId, Date date);
}
