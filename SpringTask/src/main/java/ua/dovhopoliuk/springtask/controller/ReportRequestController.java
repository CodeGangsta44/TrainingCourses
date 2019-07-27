package ua.dovhopoliuk.springtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.dovhopoliuk.springtask.entity.ReportRequest;
import ua.dovhopoliuk.springtask.service.ReportRequestService;

@RestController
@RequestMapping(value = "/api/reportRequests")
public class ReportRequestController {
    private ReportRequestService reportRequestService;

    @Autowired
    public ReportRequestController(ReportRequestService reportRequestService) {
        this.reportRequestService = reportRequestService;
    }

    @PostMapping(value = "/{reportRequest}")
    public void processRequest(@PathVariable ReportRequest reportRequest, boolean answer) {
        if (answer) {
            reportRequestService.approve(reportRequest);
        } else {
            reportRequestService.reject(reportRequest);
        }
    }
}
