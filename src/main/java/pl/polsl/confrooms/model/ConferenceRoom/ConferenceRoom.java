package pl.polsl.confrooms.model.ConferenceRoom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//MAPOWANIE OBIEKTOWO RELACYJNE - ORM
//STRUKTURA TABELI W BAZIE DANYCH
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ConferenceRooms")
public class ConferenceRoom {
    @Id
    @SequenceGenerator(
            name="conference_room_sequence",
            sequenceName = "conference_roomn_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conference_room_sequence"
    )
    private Long id;
    private Long ownerId;
    private Integer numberOfSeats;
    private String name;
    private String description;
    private String homeNumber;
    private String street;
    private Boolean ledScreen;
    private Boolean airConditioning;
    private Boolean internet;
    private Boolean soundSystem;
    private Double price;


    public ConferenceRoom(Long ownerId, Integer numberOfSeats, String name, String description, String homeNumber, String street, Boolean ledScreen, Boolean airConditioning, Boolean internet, Boolean soundSystem, Double price) {
        this.ownerId = ownerId;
        this.numberOfSeats = numberOfSeats;
        this.name = name;
        this.description = description;
        this.homeNumber = homeNumber;
        this.street = street;
        this.ledScreen = ledScreen;
        this.airConditioning = airConditioning;
        this.internet = internet;
        this.soundSystem = soundSystem;
        this.price = price;
    }
}
