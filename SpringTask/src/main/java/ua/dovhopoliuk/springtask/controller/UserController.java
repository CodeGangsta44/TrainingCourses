package ua.dovhopoliuk.springtask.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.dovhopoliuk.springtask.dto.ConferenceDTO;
import ua.dovhopoliuk.springtask.dto.RegNoteDTO;
import ua.dovhopoliuk.springtask.dto.SpeakerStatisticsDTO;
import ua.dovhopoliuk.springtask.dto.UserDTO;
import ua.dovhopoliuk.springtask.entity.Role;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.exception.LoginNotUniqueException;
import ua.dovhopoliuk.springtask.exception.RegNoteDTONotValidException;
import ua.dovhopoliuk.springtask.exception.UserNotAuthenticatedException;
import ua.dovhopoliuk.springtask.service.UserService;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService userService;
    private final ReloadableResourceBundleMessageSource messageSource;
    private final FileController fileController;

    @Autowired
    public UserController(UserService userService,
                          ReloadableResourceBundleMessageSource messageSource,
                          FileController fileController) {
        this.userService = userService;
        this.messageSource = messageSource;
        this.fileController = fileController;
    }

    @GetMapping
    public Set<UserDTO> getListOfUsers(){
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces="text/plain")
    public String register(@Valid RegNoteDTO note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RegNoteDTONotValidException(note, bindingResult);
        }

        log.info("{}", note);

        User user = mapRegNoteDTOToUser(note);

        try {
            userService.saveUser(user);
        } catch (LoginNotUniqueException ex) {
            note.setLogin("");
            note.setLocalizedMessage(messageSource.getMessage("exception.login.not.unique",
                        null,
                        LocaleContextHolder.getLocale()));
            ex.setRegNoteDTO(note);
            throw ex;
        }

        return messageSource.getMessage("registration.success",
                null,
                LocaleContextHolder.getLocale());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegNoteDTONotValidException.class)
    public RegNoteDTO handleValidationExceptions(
            RegNoteDTONotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        RegNoteDTO regNoteDTO = ex.getRegNoteDTO();
        Map<String, List<String>> messages = new HashMap<>();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors()
                    .forEach(objectError -> {
                        String field = ((FieldError) objectError).getField();
                        messages.putIfAbsent(field, new ArrayList<>());
                        messages.get(field).add(objectError.getDefaultMessage());
                    });

        }

        regNoteDTO.setLocalizedMessage(messageSource.getMessage("exception.validation.message",
                null,
                LocaleContextHolder.getLocale()));
        regNoteDTO.setValidationMessages(messages);
        return regNoteDTO;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginNotUniqueException.class)
    public RegNoteDTO handleLoginNotUniqueExceptions(
            LoginNotUniqueException ex) {
        return ex.getRegNoteDTO();
    }


    @GetMapping(value = "/myRoles")
    public Set<Role> getRolesOfCurrentUser() {
        return userService.getCurrentUser().getRoles();
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

    @PutMapping(produces = "text/plain")
    public String updateUser(@RequestBody UserDTO userDTO) {

            userService.updateUser(userDTO);

        return messageSource.getMessage("updating.success",
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

    @PostMapping(value = "{user}/changeAvatar")
    public void changeAvatar(@PathVariable User user, @RequestParam(value = "file") MultipartFile multipartFile) {
        System.out.println("Here");
        System.out.println(multipartFile);
        try {
            String fileName = fileController.saveFile(multipartFile);
            System.out.println(fileName);
            user.setAvatarFileName(fileName);
            userService.saveExistingUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping(value = "{user}")
    public void deleteUser(@PathVariable User user) {
        System.out.println(user);
        userService.deleteUser(user);
    }

    @GetMapping(value = "/speakerStatistics/{speaker}")
    public SpeakerStatisticsDTO getSpeakerStatistics(@PathVariable User speaker) {
        return userService.getSpeakerStatistics(speaker);
    }

    private User mapRegNoteDTOToUser(RegNoteDTO note) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);

        if (Boolean.parseBoolean(note.getIsSpeaker())) {
            roles.add(Role.SPEAKER);
        }

        log.info("{}", note);

        return User.builder()
                .surname(note.getSurname())
                .name(note.getName())
                .patronymic(note.getPatronymic())
                .login(note.getLogin())
                .email(note.getEmail())
                .avatarFileName("default.jpg")
                .password(new BCryptPasswordEncoder().encode(note.getPassword()))
                .roles(roles)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

}
