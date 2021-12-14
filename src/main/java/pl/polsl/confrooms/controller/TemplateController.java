package pl.polsl.confrooms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//CONTROLLER ODPOWIADAJACY ZA ZWRACANIE ODPOWIEDNICH WIDOKOW DLA POSZCZEGOLNYCH ZAPYTAN
@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("register")
    public String getRegistrationView() {
        return "registration/register";
    }

    @GetMapping("/")
    public String getDefaultView() {
        return "index";
    }

    @GetMapping("/test")
    public String getTestView() {
        return "EMPTY_FILE_TO_START";
    }
}
