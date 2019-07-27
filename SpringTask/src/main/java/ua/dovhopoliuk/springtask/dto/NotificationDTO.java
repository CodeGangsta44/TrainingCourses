package ua.dovhopoliuk.springtask.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NotificationDTO {
    private Long id;
    private LocalDateTime notificationDateTime;
    private String topic;
    private String message;
}
