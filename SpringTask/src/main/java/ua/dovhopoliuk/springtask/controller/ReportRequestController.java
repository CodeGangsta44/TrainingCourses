package ua.dovhopoliuk.springtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.dto.ReportRequestDTO;
import ua.dovhopoliuk.springtask.entity.ReportRequest;
import ua.dovhopoliuk.springtask.service.ReportRequestService;

import java.util.List;
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
    public List<ReportRequestDTO> getAllReportRequests() {
        return reportRequestService.getAllReportRequests().stream()
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
}
