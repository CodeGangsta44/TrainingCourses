package ua.dovhopoliuk.springtask.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegNoteDTO {

    private String surname;
    private String name;
    private String patronymic;
    private String login;

    private String email;
    private String password;

    private String isSpeaker;
}