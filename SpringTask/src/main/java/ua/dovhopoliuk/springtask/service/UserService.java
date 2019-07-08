package ua.dovhopoliuk.springtask.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.dovhopoliuk.springtask.exceptions.EmptyUserListException;
import ua.dovhopoliuk.springtask.exceptions.LoginNotUniqueException;
import ua.dovhopoliuk.springtask.repository.UserRepository;
import ua.dovhopoliuk.springtask.dto.*;
import ua.dovhopoliuk.springtask.entity.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    public UserService(UserRepository userRepository, ReloadableResourceBundleMessageSource messageSource) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
    }

    public UsersDTO getAllUsers() {
        log.info("Get list of users");
        List<User> users = userRepository.findAll();

        if (users.size() == 0) {
            String localizedMessage = messageSource.getMessage("exception.user.list.empty",
                    null,
                    LocaleContextHolder.getLocale());

            throw new EmptyUserListException("No users is system", localizedMessage);
        } else {
            log.info("Returning list of users");
            return new UsersDTO(users);
        }
    }

//    public User getUser (UserDTO userDTO){
//        log.info("Get user: " + userDTO.toString());
//
//        User user = userRepository.findByLogin(userDTO.getLogin());
//
//        if (user == null) {
//            throw new NoSuchUserException("No user with such login", userDTO.getLogin());
//        }
//
//        if (!userDTO.getPassword().equals(user.getPassword())) {
//            throw new WrongPasswordException("Wrong password for this user", userDTO.getLogin());
//        }
//
//        log.info("Returning user: " + user.toString());
//        return user;
//    }

    public void saveNewUser (User user){
        log.info("SAVING USER");

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
                String localizedMessage = messageSource.getMessage("exception.login.not.unique",
                        null,
                        LocaleContextHolder.getLocale());

                throw new LoginNotUniqueException("Entered login is not unique, please try again", localizedMessage);
            }

            throw ex;
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByLogin(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
