package pl.polsl.confrooms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//CONTROLLER ODPOWIADAJACY ZA ZWRACANIE ODPOWIEDNICH WIDOKOW DLA POSZCZEGOLNYCH ZAPYTAN
@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    @PreAuthorize("permitAll()")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("register")
    @PreAuthorize("permitAll()")
    public String getRegistrationView() {
        return "registration/register";
    }

    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public String getDefaultView() {
        return "index";
    }

}
