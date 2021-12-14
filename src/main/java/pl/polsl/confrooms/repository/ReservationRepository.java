package pl.polsl.confrooms.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polsl.confrooms.model.Reservation.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
