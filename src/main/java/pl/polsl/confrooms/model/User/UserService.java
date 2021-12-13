package pl.polsl.confrooms.model.User;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.repository.UserRepository;

//SERWIS(MODEL W MVC) ODPOWIADAJACY ZA PRACE NA UZYTKOWINKACH
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("User with username %s not found", username)));
    }

    public UserRegistrationResponse addUser(User user) {
//        sprawdzenie czy uzytkownik juz istnieje
        boolean isUserInDB = userRepository.findByUsername(user.getUsername()).isPresent();
        if (isUserInDB) {
            return new UserRegistrationResponse(false, "Błąd!", "Taki użytkownik już istnieje. Spróbuj ponownie.");
        }
//        szyfrowanie hasla
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        zapisanie uzytkownika do db
        userRepository.save(user);
        return new UserRegistrationResponse(true, "Sukces!", "Dodano konto użytkownika");
    }

    public UserPanelDataResponse getPanelData() {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedUser instanceof User) {
            return new UserPanelDataResponse(((User) loggedUser).getFirstName(), ((User) loggedUser).getLastName());
        }
        return null;
    }
}
