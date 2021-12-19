package pl.polsl.confrooms.model.User;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.model.User.Requests.UserRegistrationRequest;
import pl.polsl.confrooms.model.User.Responses.UserRegistrationResponse;

//SERWIS(MODEL W MVC) ODPOWIADAJACY ZA REJESTRACJE UZYTYKOWNIKA
@Service
@AllArgsConstructor
public class UserRegistrationService {

    private UserService userService;

    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        boolean isEmailValid = EmailValidation.isEmailValid(userRegistrationRequest.getEmail());
        if (!isEmailValid) {
            return new UserRegistrationResponse(
                    false,
                    "Błąd!",
                    "Email nie jest poprawny. Spróbuj ponownie."
            );
        }
        return userService.addUser(new User(
                userRegistrationRequest.getUsername(),
                userRegistrationRequest.getFirstName(),
                userRegistrationRequest.getLastName(),
                userRegistrationRequest.getEmail(),
                userRegistrationRequest.getPassword(),
                userRegistrationRequest.getRole().equals("TENANT") ? UserRole.TENANT : UserRole.OWNER
        ));
    }
}
