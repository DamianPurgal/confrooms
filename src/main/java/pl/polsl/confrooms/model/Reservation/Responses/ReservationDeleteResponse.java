package pl.polsl.confrooms.model.Reservation.Responses;

import lombok.AllArgsConstructor;

//ODPOWIEDZ SERWERA NA ZAPYTANIE DODANIA REZERWACJI

@AllArgsConstructor
public class ReservationDeleteResponse {
    public boolean success;
    public String title;
    public String text;
}

