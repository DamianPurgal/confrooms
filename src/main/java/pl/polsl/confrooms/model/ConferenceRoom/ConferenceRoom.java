package pl.polsl.confrooms.model.ConferenceRoom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ConferenceRooms")
public class ConferenceRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private String name;
    private String homeNumber;
    private String street;
    private boolean ledScreen;
    private boolean airConditioning;
    private boolean internet;
    private boolean soundSystem;
    private Double price;

    public ConferenceRoom(Long ownerId, String name, String homeNumber, String street, boolean ledScreen, boolean airConditioning, boolean internet, boolean soundSystem, Double price) {
        this.ownerId = ownerId;
        this.name = name;
        this.homeNumber = homeNumber;
        this.street = street;
        this.ledScreen = ledScreen;
        this.airConditioning = airConditioning;
        this.internet = internet;
        this.soundSystem = soundSystem;
        this.price = price;
    }
}
