package pl.polsl.confrooms.model.Reservation.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoom;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ReservationUserPanelResponse {

    public ConferenceRoom conferenceRoom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date date;
    Long id;
}
