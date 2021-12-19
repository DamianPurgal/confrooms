package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.polsl.confrooms.model.User.Requests.UserRegistrationRequest;
import pl.polsl.confrooms.model.User.User;
import pl.polsl.confrooms.model.User.Requests.UserEditRequest;
import pl.polsl.confrooms.model.User.UserRegistrationService;
import pl.polsl.confrooms.model.User.UserService;

//CONTROLLER ODPOWIEDZIALNY ZA REJESTRACJE UZYTKOWNIKA
@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserRegistrationService userRegistrationService;

    @GetMapping("/userPanel")
    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_OWNER')")
    public ModelAndView showUserPanel() {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/user_panel");
        response.addObject("loggedUser", userService.getUserPanelData((User) loggedUser));
        return response;
    }

    @GetMapping("/userPanel/data")
    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_OWNER')")
    public ModelAndView getUserDataDisplayedOnUserPanel() {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/user_data");
        response.addObject("loggedUser", userService.getUserPanelData((User) loggedUser));
        return response;
    }

    @PostMapping("/userPanel/deleteUser")
    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_OWNER')")
    public String deleteUser() {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SecurityContextHolder.clearContext();
        userService.deleteUser((User) loggedUser);
        return "redirect:/";
    }

    @GetMapping("/userPanel/editUser")
    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_OWNER')")
    public String showEditUserForm() {
        return "user_panel/user_edit";
    }

    @PostMapping("/userPanel/editUser")
    @PreAuthorize("hasAnyRole('ROLE_TENANT','ROLE_OWNER')")
    public ModelAndView editUser(UserEditRequest userEditRequest) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/user_edit_response");
        response.addObject("response", userService.editUser(userEditRequest, (User) loggedUser));
        return response;
    }

    @PostMapping("register")
    @PreAuthorize("permitAll()")
    public ModelAndView registerUser(UserRegistrationRequest userRegistrationRequest) {
        ModelAndView response = new ModelAndView("registration/registration_response");
        response.addObject("response", userRegistrationService.registerUser(userRegistrationRequest));
        return response;
    }
}
