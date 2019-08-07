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

    private String topic;

    private ConferenceDTO conference;

    private UserDTO speaker;

    public ReportRequestDTO(ReportRequest reportRequest) {
        this.id = reportRequest.getId();
        this.topic = reportRequest.getTopic();
        this.conference = new ConferenceDTO(reportRequest.getConference());
        this.speaker = new UserDTO(reportRequest.getSpeaker());
    }
}
