package ua.dovhopoliuk.springtask.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.dto.RegNoteDTO;
import ua.dovhopoliuk.springtask.dto.UsersDTO;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.service.UserService;

@Slf4j
@RestController
@RequestMapping(value = "/api/all_users")
public class UserListController {
    private final UserService userService;

    @Autowired
    public UserListController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.GET)
    public UsersDTO getListOfUsers(){
        return userService.getAllUsers();
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        log.warn(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"message\": \"" + ex.getMessage() + "\"}");
    }
}
