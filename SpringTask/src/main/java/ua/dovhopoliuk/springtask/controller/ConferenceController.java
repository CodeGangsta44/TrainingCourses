package ua.dovhopoliuk.springtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.dto.*;
import ua.dovhopoliuk.springtask.entity.Conference;
import ua.dovhopoliuk.springtask.entity.Report;
import ua.dovhopoliuk.springtask.service.ConferenceService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
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
    public Set<ConferenceDTO> getValidConferences(@RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer capacity) {
        System.out.println("IN METHOD!!!!");
        if (Objects.isNull(page) || Objects.isNull(capacity)) {
            return conferenceService.getAllValidConferences().stream()
                    .map(ConferenceDTO::new)
                    .collect(Collectors.toSet());
        }

        return conferenceService.getConferencesByPage(page, capacity).stream()
                .map(ConferenceDTO::new)
                .collect(Collectors.toSet());
    }

//    @GetMapping
//    public Set<ConferenceDTO> getConferencesByPage(@RequestParam Integer currentPage, @RequestParam Integer currentCapacity) {
//        return conferenceService.getConferencesByPage(currentPage, currentCapacity).stream()
//                .map(ConferenceDTO::new)
//                .collect(Collectors.toSet());
//    }

    @GetMapping("/totalNumber")
    public Integer getTotalNumberOfValidConferences() {
        return conferenceService.getTotalNumberOfValidConferences();
    }

    @GetMapping(value = "/requests")
    public Set<ConferenceDTO> getAllConferenceRequests() {
        return conferenceService.getAllNotApprovedConferences().stream()
                .map(ConferenceDTO::new)
                .collect(Collectors.toSet());
    }

    @GetMapping(value = "/finished")
    public Set<FinishedConferenceDTO> getAllFinishedConferences() {
        return conferenceService.getAllFinishedConferences().stream()
                .map(FinishedConferenceDTO::new)
                .collect(Collectors.toSet());
    }

    @GetMapping(value = "/notFinished")
    public Set<ConferenceDTO> getAllNotFinishedConferences() {
        return conferenceService.getAllNotFinishedConferences().stream()
                .map(ConferenceDTO::new)
                .collect(Collectors.toSet());
    }

    @GetMapping(value = "/me")
    public  Set<ConferenceDTO> getAllConferencesByCurrentUser(){
        return conferenceService.getAllConferencesByCurrentUser().stream()
                .map(ConferenceDTO::new)
                .collect(Collectors.toSet());
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
        conferenceService.changeRegistration(conferenceId);
        return getConferenceById(conferenceId);
    }

    @GetMapping(value = "{conferenceId}/reports")
    public Set<ReportDTO> getReports(@PathVariable Long conferenceId) {
        return conferenceService.getReportsById(conferenceId).stream()
                .map(ReportDTO::new).collect(Collectors.toSet());
    }

    @DeleteMapping(value = "{conferenceId}/reports/{reportId}")
    public void deleteReport(@PathVariable Long conferenceId, @PathVariable Long reportId) {
        conferenceService.deleteReport(conferenceId, reportId);
    }

    @PostMapping(value = "/{conference}/processRequest")
    public void processRequest(@PathVariable Conference conference, @RequestBody boolean answer) {
        System.out.println(answer);
        if (answer) {
            conferenceService.approve(conference);
        } else {
            conferenceService.reject(conference);
        }
    }

    @PutMapping(value = "/{conference}/finish")
    public void finishConference(@PathVariable Conference conference, @RequestBody Long numberOfVisitedGuests){
        conferenceService.finish(conference, numberOfVisitedGuests);
    }
}
