package pl.polsl.confrooms.model.Reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
//MAPOWANIE OBIEKTOWO RELACYJNE - ORM
//STRUKTURA TABELI W BAZIE DANYCH
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long conferenceRoomId;
    private Long tenantId;
    private java.sql.Date date;

    public Reservation(Long conferenceRoomId, Long tenantId, Date date) {
        this.conferenceRoomId = conferenceRoomId;
        this.tenantId = tenantId;
        this.date = date;
    }
}
