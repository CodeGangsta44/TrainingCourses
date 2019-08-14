package ua.dovhopoliuk.springtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ua.dovhopoliuk.springtask.dto.RegisteredGuestDTO;
import ua.dovhopoliuk.springtask.dto.ReportDTO;
import ua.dovhopoliuk.springtask.entity.Conference;
import ua.dovhopoliuk.springtask.entity.Report;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.exception.ConferenceNotValidException;
import ua.dovhopoliuk.springtask.repository.ConferenceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConferenceService {
    private ConferenceRepository conferenceRepository;
    private UserService userService;
    private ReportService reportService;

    @Autowired
    ConferenceService(ConferenceRepository conferenceRepository,
                      UserService userService,
                      ReportService reportService) {

        this.conferenceRepository = conferenceRepository;
        this.userService = userService;
        this.reportService = reportService;
    }

    public List<Conference> getAllValidConferences() {
        return this.conferenceRepository.findAllByApprovedIsTrueAndFinishedIsFalse();
    }

    public List<Conference> getAllNotApprovedConferences() {
        return this.conferenceRepository.findAllByApprovedIsFalse();
    }

    public List<Conference> getConferencesByPage(Integer page, Integer capacity) {
        Pageable pageRequest = PageRequest.of(page - 1, capacity);
        return conferenceRepository.findAllByApprovedIsTrueAndFinishedIsFalse(pageRequest);
    }

    public Integer getTotalNumberOfValidConferences() {
        return conferenceRepository.countAllByApprovedIsTrueAndFinishedIsFalse().intValue();
    }

    public List<Conference> getAllFinishedConferences() {
        return this.conferenceRepository.findAllByFinishedIsTrue();
    }

    public List<Conference> getAllNotFinishedConferences() {
        return this.conferenceRepository.findAllByFinishedIsFalseAndApprovedIsTrue();
    }

    public List<Conference> getAllConferencesByCurrentUser() {
        return this.conferenceRepository.findAllByRegisteredGuestsContainsAndApprovedIsTrueAndFinishedIsFalse(userService.getCurrentUser());
    }

    public Conference getConferenceById(Long id){
        return this.conferenceRepository.findConferenceById(id);
    }

    public void addNewConference(Conference conference) {
        conferenceRepository.save(conference);
    }

    public void deleteConferenceById(Long id) {
        conferenceRepository.deleteById(id);
    }

    public void updateConferenceById(Long id, Conference newConference) {
        Conference oldConference = conferenceRepository.findConferenceById(id);
        copyUpdatableFields(oldConference, newConference);
        conferenceRepository.save(oldConference);
    }

    private void copyUpdatableFields(Conference oldConf, Conference newConf) {

        if (!Objects.isNull(newConf.getTopic())) {
            oldConf.setTopic(newConf.getTopic());
        }

        if (!Objects.isNull(newConf.getEventDateTime())) {
            oldConf.setEventDateTime(newConf.getEventDateTime());
        }

        if (!Objects.isNull(newConf.getEventAddress())) {
            oldConf.setEventAddress(newConf.getEventAddress());
        }

        if (!Objects.isNull(newConf.getDescription())) {
            oldConf.setDescription(newConf.getDescription());
        }
    }

    public Set<User> getRegisteredUsers(Long conferenceId) {
        return conferenceRepository
                .findConferenceById(conferenceId)
                .getRegisteredGuests();
    }

    public void changeRegistration(Long conferenceId) {
        Conference conference = conferenceRepository.findConferenceById(conferenceId);
        User user = userService.getCurrentUser();

        if (!conference.isApproved() || conference.isFinished()) {
            throw new ConferenceNotValidException();
        }
        if (!conference.getRegisteredGuests().contains(user)) {
            conference.getRegisteredGuests().add(user);
        } else {
            conference.getRegisteredGuests().remove(user);
        }
        conferenceRepository.save(conference);
        userService.saveExistingUser(user);
    }

    public boolean isUserRegistered(Conference conference) {
        Long userId = userService.getIdOfCurrentUser();
        return conference.getRegisteredGuests()
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet()
                ).contains(userId);
    }

    public void cancelRegistrationOfUser(Long conferenceId, Long userId) {
        Conference conference = conferenceRepository.findConferenceById(conferenceId);
        conference.getRegisteredGuests().remove(userService.getUserById(userId));

        conferenceRepository.save(conference);
    }

    public Set<Report> getReportsById(Long conferenceId) {
        return conferenceRepository.findConferenceById(conferenceId).getReports();
    }

    public void deleteReport(Long conferenceId, Long reportId) {
        Conference conference = conferenceRepository.findConferenceById(conferenceId);
        Report report = reportService.getReportById(reportId);

        conference.getReports().remove(report);

        conferenceRepository.save(conference);
    }

    public void approve(Conference conference) {
        conference.setApproved(true);
        conferenceRepository.save(conference);
    }

    public void reject(Conference conference) {
        conferenceRepository.delete(conference);
    }

    public void finish(Conference conference, Long numberOfVisitedGuests) {
        conference.setNumberOfVisitedGuests(numberOfVisitedGuests);
        conference.setFinished(true);
        conferenceRepository.save(conference);
    }
}
