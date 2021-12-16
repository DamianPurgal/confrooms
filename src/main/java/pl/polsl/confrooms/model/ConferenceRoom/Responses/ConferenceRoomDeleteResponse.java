package pl.polsl.confrooms.model.ConferenceRoom.Responses;

import lombok.AllArgsConstructor;

//ODPOWIEDZ SERWERA NA ZAPYTANIE USUNIECIA SALI KONFERENCYJNEJ
@AllArgsConstructor
public class ConferenceRoomDeleteResponse {
    public String title;
    public String text;
}
