package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.polsl.confrooms.model.User.Requests.UserRegistrationRequest;
import pl.polsl.confrooms.model.User.UserRegistrationService;

//CONTROLLER ODPOWIEDZIALNY ZA REJESTRACJE UZYTKOWNIKA
@Controller
@AllArgsConstructor
public class UserRegistrationController {

    private UserRegistrationService userRegistrationService;

    @PostMapping("register")
    public ModelAndView registerUser(UserRegistrationRequest userRegistrationRequest) {
        ModelAndView response = new ModelAndView("registration/registration_response");
        response.addObject("response", userRegistrationService.registerUser(userRegistrationRequest));
        return response;
    }
}
