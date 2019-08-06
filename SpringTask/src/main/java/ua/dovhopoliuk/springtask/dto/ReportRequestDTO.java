package ua.dovhopoliuk.springtask.dto;

import lombok.*;
import ua.dovhopoliuk.springtask.entity.ReportRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class ReportRequestDTO {
    private Long id;
    private ReportDTO report;

    public ReportRequestDTO(ReportRequest reportRequest) {
        this.id = reportRequest.getId();
        this.report = new ReportDTO(reportRequest.getReport());
    }
}
