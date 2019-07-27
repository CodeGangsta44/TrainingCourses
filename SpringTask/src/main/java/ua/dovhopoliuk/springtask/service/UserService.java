package ua.dovhopoliuk.springtask.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.dovhopoliuk.springtask.exception.EmptyUserListException;
import ua.dovhopoliuk.springtask.exception.LoginNotUniqueException;
import ua.dovhopoliuk.springtask.repository.UserRepository;
import ua.dovhopoliuk.springtask.dto.*;
import ua.dovhopoliuk.springtask.entity.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<UserDTO> getAllUsers() {
        log.info("Get list of users");
        List<User> users = userRepository.findAll();

        if (users.size() == 0) {
            String localizedMessage = messageSource.getMessage("exception.user.list.empty",
                    null,
                    LocaleContextHolder.getLocale());

            throw new EmptyUserListException("No users is system", localizedMessage);
        } else {
            log.info("Returning list of users");
            return users.stream().map(UserDTO::new).collect(Collectors.toSet());
        }
    }

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

    public void saveExistingUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findUserByLogin(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public Long getIdOfCurrentUser() {
        return ((User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
    }

    public User getCurrentUser() {
        return userRepository.findUserById(getIdOfCurrentUser());
    }
}
