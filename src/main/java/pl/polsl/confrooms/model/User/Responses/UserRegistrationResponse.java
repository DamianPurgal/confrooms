package pl.polsl.confrooms.model.User.Responses;

import lombok.AllArgsConstructor;

//ODPOWIEDZ SERWERA NA WYSLANE PRZEZ UZYTKOWNIKA POPRAWNE(W ODPOWIEDNIM FORMACIE(UserRegistrationRequest)) DANE
@AllArgsConstructor
public class UserRegistrationResponse {
    public boolean success;
    public String title;
    public String text;
}
