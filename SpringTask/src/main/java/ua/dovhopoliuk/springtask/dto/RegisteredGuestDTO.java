package ua.dovhopoliuk.springtask.dto;

import lombok.*;
import ua.dovhopoliuk.springtask.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisteredGuestDTO {
    private Long id;
    private String login;
    private String surname;
    private String name;

    public RegisteredGuestDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.surname = user.getSurname();
        this.name = user.getName();
    }
}
