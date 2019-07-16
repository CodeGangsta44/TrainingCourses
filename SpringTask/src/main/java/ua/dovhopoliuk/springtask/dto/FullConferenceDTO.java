package ua.dovhopoliuk.springtask.dto;

import lombok.*;
import ua.dovhopoliuk.springtask.entity.Conference;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class FullConferenceDTO {
    private Long id;
    private String topic;
    private LocalDateTime eventDateTime;
    private String eventAddress;

    private Set<UserDTO> registeredGuests;
    private Set<ReportDTO> reports;

    private String description;
    private boolean registered;

    public FullConferenceDTO(Conference conference, boolean registered) {
        this.id = conference.getId();
        this.topic = conference.getTopic();
        this.eventDateTime = conference.getEventDateTime();
        this.eventAddress = conference.getEventAddress();

        this.registeredGuests = conference.getRegisteredGuests()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toSet());

        this.reports = conference.getReports()
                .stream()
                .map(ReportDTO::new)
                .collect(Collectors.toSet());

        this.description = conference.getDescription();
        this.registered = registered;
    }
}
