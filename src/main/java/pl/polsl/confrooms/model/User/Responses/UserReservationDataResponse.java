package pl.polsl.confrooms.model.User.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserReservationDataResponse {
    public Long id;
    public String firstName;
    public String lastName;
    public String username;
    public String email;
}

