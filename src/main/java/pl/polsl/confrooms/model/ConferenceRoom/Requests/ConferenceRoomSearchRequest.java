package pl.polsl.confrooms.model.ConferenceRoom.Requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//ZAPYTANIE DO SERWERA NA TEMAT WYSZUKANIA SAL KONFERENCYJNYCH
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ConferenceRoomSearchRequest {
    private Integer numberOfSeats;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String ledScreen;
    private String airConditioning;
    private String internet;
    private String soundSystem;
}
