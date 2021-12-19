package pl.polsl.confrooms.model.User;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.model.User.Requests.UserEditRequest;
import pl.polsl.confrooms.model.User.Responses.UserEditResponse;
import pl.polsl.confrooms.model.User.Responses.UserPanelDataResponse;
import pl.polsl.confrooms.model.User.Responses.UserRegistrationResponse;
import pl.polsl.confrooms.model.User.Responses.UserReservationDataResponse;
import pl.polsl.confrooms.repository.UserRepository;

//SERWIS(MODEL W MVC) ODPOWIADAJACY ZA PRACE NA UZYTKOWINKACH
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Nie znaleziono uzytkownika o nazwie %s", username)));
    }

    public UserRegistrationResponse addUser(User user) {
//        sprawdzenie czy uzytkownik juz istnieje
        boolean isUserInDB = userRepository.findByUsername(user.getUsername()).isPresent();
        if (isUserInDB) {
            return new UserRegistrationResponse(
                    false,
                    "Błąd!",
                    "Taki użytkownik już istnieje. Spróbuj ponownie."
            );
        }
//        szyfrowanie hasla
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        zapisanie uzytkownika do db
        userRepository.save(user);
        return new UserRegistrationResponse(
                true,
                "Sukces!",
                "Dodano konto użytkownika"
        );
    }

    public UserPanelDataResponse getUserPanelData(User user) {
        return new UserPanelDataResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public UserReservationDataResponse getDataToDisplayOnReservation(User user) {
        return new UserReservationDataResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public UserEditResponse editUser(UserEditRequest userEditRequest, User user) {
        boolean isEmailValid = EmailValidation.isEmailValid(userEditRequest.getEmail());
        if (!isEmailValid) {
            return new UserEditResponse(
                    false,
                    "Błąd!",
                    "Email nie jest poprawny. Spróbuj ponownie."
            );
        }
        if (!bCryptPasswordEncoder.matches(userEditRequest.getOldPassword(), user.getPassword())) {
            return new UserEditResponse(
                    false,
                    "Błąd!",
                    "Hasło nie jest poprawne. Spróbuj ponownie."
            );
        }

        user.setFirstName(userEditRequest.getFirstName());
        user.setLastName(userEditRequest.getLastName());
        user.setEmail(userEditRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userEditRequest.getNewPassword()));

        userRepository.save(user);
        return new UserEditResponse(
                true,
                "Sukces!",
                "Zedytowano."
        );

    }
}
