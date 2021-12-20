package pl.polsl.confrooms.model.Reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

//MAPOWANIE OBIEKTOWO RELACYJNE - ORM
//STRUKTURA TABELI W BAZIE DANYCH
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Reservations")
public class Reservation {
    @Id
    @SequenceGenerator(
            name="reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Long id;
    private Long conferenceRoomId;
    private Long tenantId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Reservation(Long conferenceRoomId, Long tenantId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        this.conferenceRoomId = conferenceRoomId;
        this.tenantId = tenantId;
        this.date = date;
    }
}
