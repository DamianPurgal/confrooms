package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.polsl.confrooms.model.User.User;
import pl.polsl.confrooms.model.User.UserService;

//CONTROLLER ODPOWIEDZIALNY ZA REJESTRACJE UZYTKOWNIKA
@Controller
@RequestMapping("/userPanel")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    @ResponseBody
    public ModelAndView loggedUser()
    {
        ModelAndView response = new ModelAndView("user_panel/user_panel");
        response.addObject("loggedUser",userService.getPanelData());
        return response;
    }

    @GetMapping("/data")
    @ResponseBody
    public ModelAndView getData()
    {
        ModelAndView response = new ModelAndView("user_panel/user_data");
        response.addObject("loggedUser",userService.getPanelData());
        return response;
    }

//    @DeleteMapping("/deleteUser")
//    @ResponseBody
//    public void deleteUser(){
//        System.out.println("jestem tu");
//        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        userService.deleteUser((User) loggedUser);
////        return "Test";
//    }
}
