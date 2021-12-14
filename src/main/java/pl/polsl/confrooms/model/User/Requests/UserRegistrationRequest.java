package pl.polsl.confrooms.model.User.Requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

//REQUEST WYSYLANY ABY ZAREJESTROWAC UZYTKOWNIKA.
//DEFINIUJE JAKIE DANE SA POTRZEBNE DO REJESTRACJI
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class UserRegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String email;
    private final String role;
}
