package ua.dovhopoliuk.springtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ua.dovhopoliuk.springtask.dto.RegisteredGuestDTO;
import ua.dovhopoliuk.springtask.dto.ReportDTO;
import ua.dovhopoliuk.springtask.entity.Conference;
import ua.dovhopoliuk.springtask.entity.Report;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.repository.ConferenceRepository;

import java.time.LocalDateTime;
import java.util.List;
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

    public List<Conference> getAllConferences() {
        return this.conferenceRepository.findAll();
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
        if (newConf.getTopic() != null) {
            oldConf.setTopic(newConf.getTopic());
        }

        if (newConf.getEventDateTime() != null) {
            oldConf.setEventDateTime(newConf.getEventDateTime());
        }

        if (newConf.getEventAddress() != null) {
            oldConf.setEventAddress(newConf.getEventAddress());
        }

        if (newConf.getDescription() != null) {
            oldConf.setDescription(newConf.getDescription());
        }
    }

    public Set<User> getRegisteredUsers(Long conferenceId) {
        return conferenceRepository
                .findConferenceById(conferenceId)
                .getRegisteredGuests();
    }

    public void changeRegistration(Long conferenceId) {
        Long userId = userService.getIdOfCurrentUser();
        Conference conference = conferenceRepository.findConferenceById(conferenceId);
        User user = userService.getUserById(userId);

        if (!isUserRegistered(conference)) {
            conference.getRegisteredGuests().add(user);
            user.getPlanedConferences().add(conference);
        } else {
            conference.getRegisteredGuests().remove(user);
            user.getPlanedConferences().remove(conference);
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

    public void addReport(Long conferenceId, Report report) {
        Conference conference = conferenceRepository.findConferenceById(conferenceId);
        User speaker = userService.getCurrentUser();

        report.setSpeaker(speaker);
        reportService.saveReport(report);
        conference.getReports().add(report);

        conferenceRepository.save(conference);
    }

    public void deleteReport(Long conferenceId, Long reportId) {
        Conference conference = conferenceRepository.findConferenceById(conferenceId);
        Report report = reportService.getReportById(reportId);

        conference.getReports().remove(report);

        conferenceRepository.save(conference);
    }
}
