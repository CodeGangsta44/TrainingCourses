package ua.dovhopoliuk.springtask.dto;

import lombok.*;
import ua.dovhopoliuk.springtask.entity.Role;
import ua.dovhopoliuk.springtask.entity.User;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String login;
    private String email;
    private String avatarFileName;
    private Set<Role> roles;

    public UserDTO(User user) {
        this.id = user.getId();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.patronymic = user.getPatronymic();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.avatarFileName = user.getAvatarFileName();
        this.roles = user.getRoles();
    }
}
