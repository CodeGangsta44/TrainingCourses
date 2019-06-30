package ua.dovhopoliuk.springtask.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;
import ua.dovhopoliuk.springtask.exceptions.EmptyUserListException;
import ua.dovhopoliuk.springtask.exceptions.LoginNotUniqueException;
import ua.dovhopoliuk.springtask.exceptions.NoSuchUserException;
import ua.dovhopoliuk.springtask.exceptions.WrongPasswordException;
import ua.dovhopoliuk.springtask.repository.UserRepository;
import ua.dovhopoliuk.springtask.dto.*;
import ua.dovhopoliuk.springtask.entity.*;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersDTO getAllUsers() {
        log.info("Get list of users");
        List<User> users = userRepository.findAll();

        if (users.size() == 0) {
            throw new EmptyUserListException("No users is system");
        } else {
            log.info("Returning list of users");
            return new UsersDTO(users);
        }
    }

    public User getUser (UserDTO userDTO){
        log.info("Get user: " + userDTO.toString());

        User user = userRepository.findByLogin(userDTO.getLogin());

        if (user == null) {
            throw new NoSuchUserException("No user with such login", userDTO.getLogin());
        }

        if (userDTO.getPassword().hashCode() != user.getHashCodeOfPassword()) {
            throw new WrongPasswordException("Wrong password for this user", userDTO.getLogin());
        }

        log.info("Returning user: " + user.toString());
        return user;
    }

    public void saveNewUser (User user){

        try {
            userRepository.save(user);
        } catch (Exception ex){
            Throwable specificException = NestedExceptionUtils.getMostSpecificCause(ex);

            int errorCode = 0;

            if (specificException instanceof SQLException) {
                SQLException sqlException = (SQLException)specificException;
                errorCode = sqlException.getErrorCode();
            }

            if (errorCode == 1062) {
                log.warn("Login already exists");
                throw new LoginNotUniqueException("Entered login is not unique, please try again", user.getLogin());
            }

            throw ex;
        }

    }


}
