package ua.dovhopoliuk.springtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dovhopoliuk.springtask.entity.*;
import ua.dovhopoliuk.springtask.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportRequestService {
    private ReportRequestRepository reportRequestRepository;
    private NotificationRepository notificationRepository;
    private ReportRepository reportRepository;
    private UserService userService;

    @Autowired
    public ReportRequestService(ReportRequestRepository reportRequestRepository,
                                NotificationRepository notificationRepository,
                                ReportRepository reportRepository,
                                UserService userService) {

        this.reportRequestRepository = reportRequestRepository;
        this.notificationRepository = notificationRepository;
        this.reportRepository = reportRepository;
        this.userService = userService;
    }

    public void createReportRequest(ReportRequest reportRequest) {
        if (Objects.isNull(reportRequest.getSpeaker())) {
            reportRequest.setSpeaker(userService.getCurrentUser());
        }

        reportRequestRepository.save(reportRequest);
        approve(reportRequest);
    }

    public List<ReportRequest> getAllReportRequests() {
        return reportRequestRepository.findAll();
    }

    public List<ReportRequest> getProposedReports () {
        User speaker = userService.getCurrentUser();

        return reportRequestRepository.findAllByApprovedByModeratorIsTrue().stream()
                .filter(request -> speaker.equals(request.getSpeaker()))
                .collect(Collectors.toList());
    }

    public void approve(ReportRequest reportRequest) {
        User currentUser = userService.getCurrentUser();

        if (currentUser.getRoles().contains(Role.MODER)) {
            reportRequest.setApprovedByModerator(true);
        }

        if (currentUser.equals(reportRequest.getSpeaker())) {
            reportRequest.setApprovedBySpeaker(true);
        }

        reportRequestRepository.save(reportRequest);

        if (reportRequest.isApprovedByModerator() && reportRequest.isApprovedBySpeaker()) {
            approveRequest(reportRequest);
        }
    }

    @Transactional
    public void approveRequest(ReportRequest reportRequest) {
        Conference conference = reportRequest.getConference();
        Report report = Report.builder()
                .topic(reportRequest.getTopic())
                .conference(reportRequest.getConference())
                .speaker(reportRequest.getSpeaker()).build();
        User speaker = report.getSpeaker();

        conference.getReports().add(report);

        Notification notification = createNotification(reportRequest, speaker, conference, "approved");

        reportRepository.save(report);
        notificationRepository.save(notification);
        reportRequestRepository.delete(reportRequest);
    }

    @Transactional
    public void reject(ReportRequest reportRequest) {
        Conference conference = reportRequest.getConference();
        User speaker = reportRequest.getSpeaker();

        Notification notification = createNotification(reportRequest, speaker, conference, "rejected");

        notificationRepository.save(notification);
        reportRequestRepository.delete(reportRequest);
    }

    private Notification createNotification(ReportRequest reportRequest, User speaker, Conference conference, String status) {
        List<String> topic_values = new ArrayList<>();
        List<String> message_values = new ArrayList<>();

        topic_values.add(reportRequest.getTopic());
        message_values.add(speaker.getName());
        message_values.add(reportRequest.getTopic());
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
