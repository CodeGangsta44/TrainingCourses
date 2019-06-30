package ua.dovhopoliuk.springtask.dto;

import lombok.*;
import ua.dovhopoliuk.springtask.entity.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UsersDTO {
    private List<User> users;
}
