package ua.dovhopoliuk.springtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dovhopoliuk.springtask.dto.ReportDTO;
import ua.dovhopoliuk.springtask.entity.Conference;
import ua.dovhopoliuk.springtask.entity.Report;
import ua.dovhopoliuk.springtask.repository.ConferenceRepository;
import ua.dovhopoliuk.springtask.repository.ReportRepository;
import ua.dovhopoliuk.springtask.repository.ReportRequestRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ReportService {
    private ReportRepository reportRepository;
    private ConferenceRepository conferenceRepository;
    private ReportRequestRepository reportRequestRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository,
                         ConferenceRepository conferenceRepository,
                         ReportRequestRepository reportRequestRepository) {
        this.reportRepository = reportRepository;
        this.conferenceRepository = conferenceRepository;
        this.reportRequestRepository = reportRequestRepository;
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report getReportById(Long id) {
        return reportRepository.findReportById(id);
    }

    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    public void updateReport(ReportDTO reportDTO) {
        Report report = reportRepository.findReportById(reportDTO.getId());

        report.setTopic(reportDTO.getTopic());

        reportRepository.save(report);
    }

    public void deleteReport(Report report) {
        reportRequestRepository.deleteAllByReport(report);

        if (!Objects.isNull(report.getConference())) {
            report.getConference().getReports().remove(report);
        }

        reportRepository.delete(report);
    }
}
