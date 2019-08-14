package ua.dovhopoliuk.springtask.entity;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String topic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conference_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Conference conference;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User speaker;
}
