package pl.polsl.confrooms.model.ConferenceRoom;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ConferenceRoomAddRequest {
    private final String name;
    private final String street;
    private final String homeNumber;
    private final Double price;
}
