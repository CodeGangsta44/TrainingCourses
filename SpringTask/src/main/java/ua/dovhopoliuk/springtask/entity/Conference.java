package ua.dovhopoliuk.springtask.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "registeredGuests")
@EqualsAndHashCode(exclude = "registeredGuests")

@Entity
@Table(name = "conferences")
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id",nullable = false)
    private Long id;

    @Column(nullable = false)
    private String topic;

    @Column(name = "event_date_time", nullable = false)
    private LocalDateTime eventDateTime;

    @Column(name = "event_address", nullable = false)
    private String eventAddress;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "conference")
    private Set<Report> reports;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_conferences",
        joinColumns = {@JoinColumn( name = "conference_id")},
        inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private Set<User> registeredGuests;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean approved;

    @Column(nullable = false)
    private boolean finished;

    private Long numberOfVisitedGuests;
}
