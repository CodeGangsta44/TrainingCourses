package ua.dovhopoliuk.springtask.entity;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name="speaker_id")
    private User speaker;

    @Column(nullable = false)
    private Long totalRating;
}
