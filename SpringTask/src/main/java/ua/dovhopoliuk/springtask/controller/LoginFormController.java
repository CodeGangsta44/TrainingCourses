//package ua.dovhopoliuk.springtask.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import ua.dovhopoliuk.springtask.dto.UserDTO;
//import ua.dovhopoliuk.springtask.entity.User;
//import ua.dovhopoliuk.springtask.exceptions.LoginNotUniqueException;
//import ua.dovhopoliuk.springtask.service.UserService;
//
//@Slf4j
//@RestController
//@RequestMapping(value = "/api/login")
//public class LoginFormController {
//
//    private final UserService userService;
//
//    @Autowired
//    public LoginFormController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public User getUser(UserDTO user){
//        return userService.getUser(user);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<RuntimeException> handleRuntimeException(RuntimeException ex) {
//        log.warn("{}", ex);
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(ex);
//    }
//}
