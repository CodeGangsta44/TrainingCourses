package ua.dovhopoliuk.springtask.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table( name="user",
        uniqueConstraints={@UniqueConstraint(columnNames={"login"})})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;
    private String patronymic;
    @Column(nullable = false)
    private String login;

    private String email;

    @Column(name = "hash_code_of_password", nullable = false)
    private int hashCodeOfPassword;
}
