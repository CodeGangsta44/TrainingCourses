package ua.dovhopoliuk.springtask.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.dto.ConferenceDTO;
import ua.dovhopoliuk.springtask.dto.UserDTO;
import ua.dovhopoliuk.springtask.service.UserService;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Set<UserDTO> getListOfUsers(){
        return userService.getAllUsers();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        log.warn(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    @GetMapping(value = "{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return new UserDTO(userService.getUserById(id));
    }

    @GetMapping(value = "{id}/plannedConferences")
    public Set<ConferenceDTO> getPlannedConferencesById(@PathVariable Long id) {
        return userService.getPlannedConferencesById(id);
    }

}
