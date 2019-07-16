package ua.dovhopoliuk.springtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dovhopoliuk.springtask.entity.Report;
import ua.dovhopoliuk.springtask.repository.ReportRepository;

@Service
public class ReportService {
    private ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report getReportById(Long id) {
        return reportRepository.findReportById(id);
    }

    public void saveReport(Report report) {
        reportRepository.save(report);
    }


}
