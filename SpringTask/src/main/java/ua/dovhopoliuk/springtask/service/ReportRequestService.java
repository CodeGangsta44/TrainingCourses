package ua.dovhopoliuk.springtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dovhopoliuk.springtask.entity.*;
import ua.dovhopoliuk.springtask.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportRequestService {
    private ReportRequestRepository reportRequestRepository;
    private NotificationRepository notificationRepository;

    @Autowired
    public ReportRequestService(ReportRequestRepository reportRequestRepository,
                                NotificationRepository notificationRepository) {

        this.reportRequestRepository = reportRequestRepository;
        this.notificationRepository = notificationRepository;
    }

    public void createReportRequest(Report report, Conference conference) {
        ReportRequest reportRequest = ReportRequest.builder()
                .report(report)
                .conference(conference)
                .build();

        reportRequestRepository.save(reportRequest);
    }

    public List<ReportRequest> getAllReportRequests() {
        return reportRequestRepository.findAll();
    }

    public void approve(ReportRequest reportRequest) {
        Conference conference = reportRequest.getConference();
        Report report = reportRequest.getReport();
        User speaker = report.getSpeaker();

        conference.getReports().add(report);

        Notification notification = createNotification(report, speaker, conference, "approved");

        notificationRepository.save(notification);
        reportRequestRepository.delete(reportRequest);
    }

    public void reject(ReportRequest reportRequest) {
        Conference conference = reportRequest.getConference();
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
