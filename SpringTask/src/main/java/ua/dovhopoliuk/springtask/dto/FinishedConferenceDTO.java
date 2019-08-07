package ua.dovhopoliuk.springtask.dto;

import lombok.*;
import ua.dovhopoliuk.springtask.entity.Conference;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class FinishedConferenceDTO {
    private Long id;
    private String topic;
    private String eventDateTime;
    private String eventAddress;
    private String description;

    private Long numberOfRegisteredGuests;
    private Long numberOfVisitedGuests;

    public FinishedConferenceDTO(Conference conference) {
        this.id = conference.getId();
        this.topic = conference.getTopic();
        this.eventDateTime = conference.getEventDateTime().toString();
        this.eventAddress = conference.getEventAddress();
        this.description = conference.getDescription();

        this.numberOfRegisteredGuests = (long) conference.getRegisteredGuests().size();
        this.numberOfVisitedGuests = conference.getNumberOfVisitedGuests();
    }
}
