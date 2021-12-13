package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.polsl.confrooms.model.User.UserRegistrationRequest;
import pl.polsl.confrooms.model.User.UserRegistrationService;

//CONTROLLER ODPOWIEDZIALNY ZA REJESTRACJE UZYTKOWNIKA
@Controller
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserRegistrationController {

    private UserRegistrationService userRegistrationService;

    @PostMapping("registration")
    @ResponseBody
    public ModelAndView registerUser(UserRegistrationRequest userRegistrationRequest)
    {
        ModelAndView response = new ModelAndView("registration/registration_response");
        response.addObject("response",userRegistrationService.registerUser(userRegistrationRequest) );
        return response;
    }
}
