package ua.dovhopoliuk.springtask.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.dto.ConferenceDTO;
import ua.dovhopoliuk.springtask.dto.RegNoteDTO;
import ua.dovhopoliuk.springtask.dto.UserDTO;
import ua.dovhopoliuk.springtask.entity.Role;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.exception.LoginNotUniqueException;
import ua.dovhopoliuk.springtask.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService userService;
    private final ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    public UserController(UserService userService, ReloadableResourceBundleMessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public Set<UserDTO> getListOfUsers(){
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces="text/plain")
    public String register(RegNoteDTO note){
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        roles.add(Role.SPEAKER);

        log.info("{}", note);
        User user = User.builder()
                .surname(note.getSurname())
                .name(note.getName())
                .patronymic(note.getPatronymic())
                .login(note.getLogin())
                .email(note.getEmail())
                .password(new BCryptPasswordEncoder().encode(note.getPassword()))
                .roles(roles)
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

        return messageSource.getMessage("registration.success",
                null,
                LocaleContextHolder.getLocale());

    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RuntimeException> handleRuntimeException(RuntimeException ex) {
        log.warn(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex);
    }

    @GetMapping(value = "/me")
    public UserDTO getCurrentUser() {
        return new UserDTO(userService.getCurrentUser());
    }

    @GetMapping(value = "{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return new UserDTO(userService.getUserById(id));
    }

    @GetMapping(value = "{id}/plannedConferences")
    public Set<ConferenceDTO> getPlannedConferencesById(@PathVariable Long id) {
        //TODO implement this method
        return null;
    }

}
