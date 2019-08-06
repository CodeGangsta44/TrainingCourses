package ua.dovhopoliuk.springtask.dto;

import lombok.*;
import ua.dovhopoliuk.springtask.entity.Report;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReportDTO {
    private Long id;
    private String topic;
    private UserDTO speaker;
    private ConferenceDTO conference;

    public ReportDTO(Report report) {
        this.id = report.getId();
        this.topic = report.getTopic();
        this.speaker = new UserDTO(report.getSpeaker());
        this.conference = new ConferenceDTO(report.getConference());
    }
}
