package ua.dovhopoliuk.springtask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "planedConferences")
@EqualsAndHashCode(exclude = "planedConferences")

@Entity
@Table( name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames={"login"})})

public class User implements UserDetails {
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

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "registeredGuests")
//    @JoinTable(name = "users_conferences",
//            joinColumns = {@JoinColumn( name = "user_id")},
//            inverseJoinColumns = { @JoinColumn(name = "conference_id") }
//    )
    private Set<Conference> planedConferences;

    @Column(nullable = false)
    private String password;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
