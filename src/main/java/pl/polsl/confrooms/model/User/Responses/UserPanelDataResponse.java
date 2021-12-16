package pl.polsl.confrooms.model.User.Responses;

import lombok.AllArgsConstructor;

//ODPOWIEDZ SERWERA NA WYSLANE PRZEZ UZYTKOWNIKA ZAPYTANIA O DANE W PANELU UZYTKOWNIKA
@AllArgsConstructor
public class UserPanelDataResponse {
    public String firstName;
    public String lastName;
    public String username;
    public String email;
}
