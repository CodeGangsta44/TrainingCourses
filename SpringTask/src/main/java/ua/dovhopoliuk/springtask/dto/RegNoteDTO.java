package ua.dovhopoliuk.springtask.dto;

import lombok.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ua.dovhopoliuk.springtask.utility.RegExPatterns;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegNoteDTO {

    @NotEmpty(message = "{exception.validation.field.is.blank}")
    @Size(min = 2, max = 60, message = "{exception.validation.field.range}")
    @Pattern(regexp = RegExPatterns.SURNAME,
            message = "{exception.validation.format}")
    private String surname;

    @NotEmpty(message = "{exception.validation.field.is.blank}")
    @Size(min = 2, max = 30, message = "{exception.validation.field.range}")
    @Pattern(regexp = RegExPatterns.NAME,
            message = "{exception.validation.format}")
    private String name;

    @NotEmpty(message = "{exception.validation.field.is.blank}")
    @Size(min = 2, max = 40, message = "{exception.validation.field.range}")
    @Pattern(regexp = RegExPatterns.PATRONYMIC,
            message = "{exception.validation.format}")
    private String patronymic;

    @NotEmpty(message = "{exception.validation.field.is.blank}")
    @Size(min = 4, max = 20, message = "{exception.validation.field.range}")
    @Pattern(regexp = RegExPatterns.LOGIN,
            message = "{exception.validation.format}")
    private String login;

    @NotEmpty(message = "{exception.validation.field.is.blank}")
    @Pattern(regexp = RegExPatterns.EMAIL,
            message = "{exception.validation.format}")
    private String email;

    @NotEmpty(message = "{exception.validation.field.is.blank}")
    @Size(min = 6, max = 20, message = "{exception.validation.field.range}")
    @Pattern(regexp = RegExPatterns.PASSWORD,
            message = "{exception.validation.format}")
    private String password;

    private String isSpeaker;

    private Map<String, List<String>> validationMessages;
    private String localizedMessage;
}