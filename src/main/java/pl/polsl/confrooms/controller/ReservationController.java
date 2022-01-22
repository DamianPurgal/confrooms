package pl.polsl.confrooms.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.polsl.confrooms.model.ConferenceRoom.ConferenceRoomService;
import pl.polsl.confrooms.model.Exceptions.NotFoundException;
import pl.polsl.confrooms.model.Reservation.Reservation;
import pl.polsl.confrooms.model.Reservation.ReservationService;
import pl.polsl.confrooms.model.User.User;
import pl.polsl.confrooms.model.User.UserService;

import java.util.Date;

//CONTROLLER ODPOWIADAJACY ZA ZWRACANIE ODPOWIEDNICH WIDOKOW DLA POSZCZEGOLNYCH ZAPYTAN NA TEMAT REZERWACJI
@Controller
@RequestMapping("")
@AllArgsConstructor
public class ReservationController {

    private ConferenceRoomService conferenceRoomService;
    private ReservationService reservationService;
    private UserService userService;

    @GetMapping("/reservation")
    @PreAuthorize("hasAnyRole('ROLE_TENANT')")
    public ModelAndView getReservationView(@RequestParam(value = "id", defaultValue = "-1") Long id) {
        try {
            Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ModelAndView response = new ModelAndView("reservation/reservation");
            response.addObject("conferenceRoom", conferenceRoomService.getConferenceRoom(id));
            response.addObject("reservedDates", reservationService.getReservedDatesOfConferenceRoom(id));
            response.addObject("user", userService.getDataToDisplayOnReservation((User)loggedUser));
            return response;

        } catch (NotFoundException e) {
            ModelAndView response = new ModelAndView("reservation/reservation_response");
            response.addObject("errorMessage", "Sala nie istnieje.");
            return response;
        }
    }

    @PostMapping("/reservation")
    @PreAuthorize("hasAnyRole('ROLE_TENANT')")
    public ModelAndView createConferenceRoomReservation(@RequestParam(value = "id") Long id, @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Reservation reservation = new Reservation(
                id,
                ((User) loggedUser).getId(),
                date
        );

        ModelAndView response = new ModelAndView("reservation/reservation_create_response");
        response.addObject("response", reservationService.addConfrerenceRoomReservation(reservation));
        return response;
    }

    @GetMapping("/userPanel/Reservations")
    @PreAuthorize("hasAnyRole('ROLE_TENANT')")
    public ModelAndView getUserReservations(){
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/tenant_reservations");
        response.addObject("reservations", reservationService.getUserReservations(((User) loggedUser).getId()));
        return response;
    }
    @PostMapping("/userPanel/Reservation/remove")
    @PreAuthorize("hasAnyRole('ROLE_TENANT')")
    public ModelAndView deleteUserReservation(@RequestParam(value = "id", defaultValue = "-1") Long id){
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView response = new ModelAndView("user_panel/delete_reservation_response");
        response.addObject("response", reservationService.deleteUserReservation(id, (User)loggedUser));
        return response;
    }
}
