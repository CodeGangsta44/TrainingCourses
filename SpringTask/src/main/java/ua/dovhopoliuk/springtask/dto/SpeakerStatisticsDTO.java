package ua.dovhopoliuk.springtask.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SpeakerStatisticsDTO {
    private Long totalReports;
    private Long totalConferences;
    private Long totalPeople;
}
