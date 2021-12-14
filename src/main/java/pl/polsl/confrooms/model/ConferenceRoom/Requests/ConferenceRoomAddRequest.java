package pl.polsl.confrooms.model.ConferenceRoom.Requests;

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
    private final String ledScreen;
    private final String airConditioning;
    private final String internet;
    private final String soundSystem;
}
