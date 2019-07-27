package ua.dovhopoliuk.springtask.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    private User addressedUser;

    @Column(name = "notification_date_time", nullable = false)
    private LocalDateTime notificationDateTime;

    @Column(nullable = false)
    private String topicKey;

    @ElementCollection(targetClass = String.class)
    private List<String> topicValues;

    @Column(nullable = false)
    private String messageKey;

    @ElementCollection(targetClass = String.class)
    private List<String> messageValues;

}
