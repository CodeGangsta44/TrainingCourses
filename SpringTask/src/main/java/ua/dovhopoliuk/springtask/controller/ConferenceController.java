package ua.dovhopoliuk.springtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.dto.ConferenceDTO;
import ua.dovhopoliuk.springtask.dto.FullConferenceDTO;
import ua.dovhopoliuk.springtask.dto.RegisteredGuestDTO;
import ua.dovhopoliuk.springtask.dto.ReportDTO;
import ua.dovhopoliuk.springtask.entity.Conference;
import ua.dovhopoliuk.springtask.entity.Report;
import ua.dovhopoliuk.springtask.entity.User;
import ua.dovhopoliuk.springtask.repository.ConferenceRepository;
import ua.dovhopoliuk.springtask.service.ConferenceService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/conferences")
public class ConferenceController {
    private ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public Set<ConferenceDTO> getAllConferences() {
        return conferenceService.getAllConferences().stream().map(ConferenceDTO::new).collect(Collectors.toSet());
    }

    @GetMapping(value = "{id}")
    public FullConferenceDTO getConferenceById(@PathVariable Long id){
        Conference conference = conferenceService.getConferenceById(id);
        boolean registered = conferenceService.isUserRegistered(conference);

        return new FullConferenceDTO(conference, registered);
    }

    @PostMapping
    public void addNewConference(ConferenceDTO conferenceDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        System.out.println("CREATING CONFERENCE");
        System.out.println(conferenceDTO.toString());

        Conference conference = Conference.builder()
                .topic(conferenceDTO.getTopic())
                .eventDateTime(LocalDateTime.parse(conferenceDTO.getEventDateTime(), formatter))
                .eventAddress(conferenceDTO.getEventAddress())
                .description(conferenceDTO.getDescription())
                .build();

        conferenceService.addNewConference(conference);
    }

    @DeleteMapping(value = "{id}")
    public void deleteConferenceById(@PathVariable Long id) {
        conferenceService.deleteConferenceById(id);
    }

    @PutMapping(value = "{id}")
    public void updateConference(@PathVariable Long id, Conference conference) {
        conferenceService.updateConferenceById(id, conference);
    }

    @GetMapping(value = "{conferenceId}/registeredGuests")
    public Set<RegisteredGuestDTO> getRegisteredUsers(@PathVariable Long conferenceId) {
        return conferenceService.getRegisteredUsers(conferenceId).stream()
                .map(RegisteredGuestDTO::new).collect(Collectors.toSet());
    }

    @GetMapping(value = "{conferenceId}/changeRegistration")
        public FullConferenceDTO changeRegistration(@PathVariable Long conferenceId) {
        System.out.println("REGISTRATION");
        conferenceService.changeRegistration(conferenceId);
        return getConferenceById(conferenceId);
    }

//    @PostMapping(value = "{conferenceId}/registeredGuests/{userId}")
//    public void registerUser(@PathVariable Long conferenceId, @PathVariable Long userId) {
//        conferenceService.registerUser(conferenceId, userId);
//    }

//    @DeleteMapping(value = "{conferenceId}/registeredGuests/{userId}")
//    public void cancelRegistrationOfUser(@PathVariable Long conferenceId, @PathVariable Long userId) {
//        conferenceService.cancelRegistrationOfUser(conferenceId, userId);
//    }

    @GetMapping(value = "{conferenceId}/reports")
    public Set<ReportDTO> getReports(@PathVariable Long conferenceId) {
        return conferenceService.getReportsById(conferenceId).stream()
                .map(ReportDTO::new).collect(Collectors.toSet());
    }

    @PostMapping(value = "{conferenceId}/addReport")
    public void addReport(@PathVariable Long conferenceId, Report report) {
        conferenceService.addReport(conferenceId, report);
    }

//    @PostMapping(value = "{conferenceId}/reports/{reportId}")
//    public void addReport(@PathVariable Long conferenceId, @PathVariable Long reportId) {
//        conferenceService.addReport(conferenceId, reportId);
//    }

    @DeleteMapping(value = "{conferenceId}/reports/{reportId}")
    public void deleteReport(@PathVariable Long conferenceId, @PathVariable Long reportId) {
        conferenceService.deleteReport(conferenceId, reportId);
    }
}
