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
@EqualsAndHashCode

@Entity
@Table( name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames={"login"})})

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;
    private String patronymic;
    @Column(nullable = false)
    private String login;

    private String email;

    private String avatarFileName;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "registeredGuests")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Conference> planedConferences;

    @EqualsAndHashCode.Exclude
    @Column(nullable = false)
    private String password;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "boolean default true")
    private boolean accountNonExpired;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "boolean default true")
    private boolean accountNonLocked;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "boolean default true")
    private boolean credentialsNonExpired;

    @EqualsAndHashCode.Exclude
    @Column(columnDefinition = "boolean default true")
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
