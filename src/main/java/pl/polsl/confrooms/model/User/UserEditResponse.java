package pl.polsl.confrooms.model.User;

import lombok.AllArgsConstructor;

//ODPOWIEDZ SERWERA NA WYSLANE PRZEZ UZYTKOWNIKA POPRAWNE(W ODPOWIEDNIM FORMACIE(UserEditRequest)) DANE
@AllArgsConstructor
public class UserEditResponse {
    public boolean success;
    public String title;
    public String text;
}
