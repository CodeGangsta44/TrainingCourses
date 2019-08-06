package ua.dovhopoliuk.springtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dovhopoliuk.springtask.entity.*;
import ua.dovhopoliuk.springtask.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportRequestService {
    private ReportRequestRepository reportRequestRepository;
    private NotificationRepository notificationRepository;
    private ConferenceRepository conferenceRepository;
    private UserService userService;

    @Autowired
    public ReportRequestService(ReportRequestRepository reportRequestRepository,
                                NotificationRepository notificationRepository,
                                ConferenceRepository conferenceRepository,
                                UserService userService) {

        this.reportRequestRepository = reportRequestRepository;
        this.notificationRepository = notificationRepository;
        this.conferenceRepository = conferenceRepository;
        this.userService = userService;
    }

    public void createReportRequest(Report report) {

        ReportRequest reportRequest = ReportRequest.builder()
                .report(report)
                .approvedBySpeaker(false)
                .approvedByModerator(false)
                .build();

        reportRequestRepository.save(reportRequest);
        approve(reportRequest);
    }

    public List<ReportRequest> getAllReportRequests() {
        return reportRequestRepository.findAll();
    }

    public List<ReportRequest> getProposedReports () {
        User speaker = userService.getCurrentUser();
        System.out.println(speaker);
        System.out.println("GG");

        return reportRequestRepository.findAllByApprovedByModeratorIsTrue().stream()
                .peek(request -> System.out.println(request.getReport().getSpeaker()))
                .filter(request -> speaker.equals(request.getReport().getSpeaker()))
                .collect(Collectors.toList());
    }

    public void approve(ReportRequest reportRequest) {
        User currentUser = userService.getCurrentUser();
        System.out.println(currentUser.toString());

        System.out.println(currentUser.equals(reportRequest.getReport().getSpeaker()));

        if (currentUser.getRoles().contains(Role.MODER)) {
            System.out.println("IS MODER");
            reportRequest.setApprovedByModerator(true);
        }

        if (currentUser.equals(reportRequest.getReport().getSpeaker())) {
            System.out.println("IS SPEAKER");
            reportRequest.setApprovedBySpeaker(true);
        }

        reportRequestRepository.save(reportRequest);

        if (reportRequest.isApprovedByModerator() && reportRequest.isApprovedBySpeaker()) {
            System.out.println("APPROVING");
            approveRequest(reportRequest);
        }
    }

    private void approveRequest(ReportRequest reportRequest) {
        Conference conference = reportRequest.getReport().getConference();
        Report report = reportRequest.getReport();
        User speaker = report.getSpeaker();

        conference.getReports().add(report);

        Notification notification = createNotification(report, speaker, conference, "approved");

        notificationRepository.save(notification);
        conferenceRepository.save(conference);
        reportRequestRepository.delete(reportRequest);
    }

    public void reject(ReportRequest reportRequest) {
        Conference conference = reportRequest.getReport().getConference();
        Report report = reportRequest.getReport();
        User speaker = report.getSpeaker();

        Notification notification = createNotification(report, speaker, conference, "rejected");

        notificationRepository.save(notification);
        reportRequestRepository.delete(reportRequest);
    }

    private Notification createNotification(Report report, User speaker, Conference conference, String status) {
        List<String> topic_values = new ArrayList<>();
        List<String> message_values = new ArrayList<>();

        topic_values.add(report.getTopic());
        message_values.add(speaker.getName());
        message_values.add(report.getTopic());
        message_values.add(conference.getTopic());

        return Notification.builder()
                .addressedUser(speaker)
                .notificationDateTime(LocalDateTime.now())
                .topicKey("topic.report.request." + status)
                .topicValues(topic_values)
                .messageKey("message.report.request." + status)
                .messageValues(message_values)
                .build();
    }
}
