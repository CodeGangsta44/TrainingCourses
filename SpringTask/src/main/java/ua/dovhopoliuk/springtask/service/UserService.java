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
import org.springframework.transaction.annotation.Transactional;
import ua.dovhopoliuk.springtask.exception.EmptyUserListException;
import ua.dovhopoliuk.springtask.exception.LoginNotUniqueException;
import ua.dovhopoliuk.springtask.exception.UserNotAuthenticatedException;
import ua.dovhopoliuk.springtask.repository.*;
import ua.dovhopoliuk.springtask.dto.*;
import ua.dovhopoliuk.springtask.entity.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ReloadableResourceBundleMessageSource messageSource;
    private final ReportRepository reportRepository;
    private final ReportRequestRepository reportRequestRepository;
    private final ConferenceRepository conferenceRepository;
    private final NotificationRepository notificationRepository;
    public final ReportService reportService;

    @Autowired
    public UserService(UserRepository userRepository,
                       ReloadableResourceBundleMessageSource messageSource,
                       ReportRepository reportRepository,
                       ReportRequestRepository reportRequestRepository,
                       ConferenceRepository conferenceRepository,
                       NotificationRepository notificationRepository,
                       ReportService reportService) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.reportRepository = reportRepository;
        this.reportRequestRepository = reportRequestRepository;
        this.conferenceRepository = conferenceRepository;
        this.notificationRepository = notificationRepository;
        this.reportService = reportService;
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

    public void saveUser (User user){
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
        return userRepository.findUserById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Long getIdOfCurrentUser() {
        log.info("GETTING ID OF CURRENT ID");
        try {
            User user = (User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } catch (RuntimeException e) {
            throw new UserNotAuthenticatedException();
        }
        return ((User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
    }

    public User getCurrentUser() {
        return userRepository.findUserById(getIdOfCurrentUser()).orElse(null);
    }

    public void updateUser(UserDTO userDTO) {
        User target = userRepository.findUserById(userDTO.getId()).orElseThrow(RuntimeException::new);
        mapSourceToTarget(userDTO, target);

        saveUser(target);
    }

    private void mapSourceToTarget(UserDTO source, User target) {
        target.setSurname(source.getSurname());
        target.setName(source.getName());
        target.setPatronymic(source.getPatronymic());
        target.setLogin(source.getLogin());
        target.setEmail(source.getEmail());
        target.setRoles(source.getRoles());
    }

    @Transactional
    public void deleteUser(User user) {

        List<Report> userReports = reportRepository.findAllBySpeaker(user);

        userReports.forEach(reportService::deleteReport);

        List<Conference> userConferences = conferenceRepository.findAllByRegisteredGuestsContains(user);
        userConferences.forEach(conference -> conference.getRegisteredGuests().remove(user));

        notificationRepository.deleteAllByAddressedUser(user);
        userRepository.delete(user);
    }
}
