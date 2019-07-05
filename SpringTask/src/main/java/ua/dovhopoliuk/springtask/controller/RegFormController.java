package ua.dovhopoliuk.springtask.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.dto.RegNoteDTO;
import ua.dovhopoliuk.springtask.entity.Role;
import ua.dovhopoliuk.springtask.exceptions.LoginNotUniqueException;
import ua.dovhopoliuk.springtask.service.UserService;
import ua.dovhopoliuk.springtask.entity.User;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping(value = "/api/registration")
public class RegFormController {
    private final UserService userService;

    @Autowired
    public RegFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void register(RegNoteDTO note){
        log.info("{}", note);
        User user = User.builder()
                .surname(note.getSurname())
                .name(note.getName())
                .patronymic(note.getPatronymic())
                .login(note.getLogin())
                .email(note.getEmail())
                .password(new BCryptPasswordEncoder().encode(note.getPassword()))
                .roles(Arrays.asList(Role.USER, Role.SPEAKER))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        log.info(user.toString());

        try {
            userService.saveNewUser(user);
        } catch (LoginNotUniqueException ex) {
            note.setLogin("");
            ex.setNote(note);
            throw ex;
        }

    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RuntimeException> handleRuntimeException(RuntimeException ex) {
        log.warn(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex);
    }
}
