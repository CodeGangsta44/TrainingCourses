package ua.dovhopoliuk.springtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.dto.ReportRequestDTO;
import ua.dovhopoliuk.springtask.entity.Conference;
import ua.dovhopoliuk.springtask.entity.ReportRequest;
import ua.dovhopoliuk.springtask.service.ReportRequestService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/reportRequests")
public class ReportRequestController {
    private ReportRequestService reportRequestService;

    @Autowired
    public ReportRequestController(ReportRequestService reportRequestService) {
        this.reportRequestService = reportRequestService;
    }

    @GetMapping
    public List<ReportRequestDTO> getRequestedReports() {
        return reportRequestService.getAllReportRequests().stream()
                .filter(Predicate.not(ReportRequest::isApprovedByModerator))
                .map(ReportRequestDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/me")
    public List<ReportRequestDTO> getProposedReports() {
        return reportRequestService.getProposedReports().stream()
                .map(ReportRequestDTO::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/{reportRequest}")
    public void processRequest(@PathVariable ReportRequest reportRequest, @RequestBody boolean answer) {
        System.out.println(answer);
        if (answer) {
            reportRequestService.approve(reportRequest);
        } else {
            reportRequestService.reject(reportRequest);
        }
    }
    @PostMapping(value = "/request/{conference}")
    public void createReportRequest(@PathVariable Conference conference, @RequestBody ReportRequest reportRequest) {
        reportRequest.setConference(conference);
        reportRequestService.createReportRequest(reportRequest);
    }
}
