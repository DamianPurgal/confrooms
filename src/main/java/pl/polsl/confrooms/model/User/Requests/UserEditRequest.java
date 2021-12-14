package pl.polsl.confrooms.model.User.Requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

//REQUEST WYSYLANY ABY EDYTOWAĆ DANE UZYTKOWNIKA.
//DEFINIUJE JAKIE DANE SA POTRZEBNE DO EDYCJI
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class UserEditRequest {
    private final String firstName;
    private final String lastName;
    private final String oldPassword;
    private final String newPassword;
    private final String email;
}
