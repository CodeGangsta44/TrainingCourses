package ua.dovhopoliuk.springtask.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "conferences")
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String topic;

    @Column(name = "event_date_time", nullable = false)
    private LocalDateTime eventDateTime;

    @Column(name = "event_address", nullable = false)
    private String eventAddress;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Report> reports;

    @ManyToMany(mappedBy = "planedConferences")
    private Set<User> registeredGuests;

    @Column(nullable = false)
    private String description;
}
