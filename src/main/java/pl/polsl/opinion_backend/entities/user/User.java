package pl.polsl.opinion_backend.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.polsl.opinion_backend.entities.base.DateAuditable;
import pl.polsl.opinion_backend.entities.role.RoleGroup;
import pl.polsl.opinion_backend.enums.role.RoleGroupEnum;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.polsl.opinion_backend.enums.role.RoleGroupEnum.ADMIN;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "\"user\"")
@NoArgsConstructor
@AllArgsConstructor
public class User extends DateAuditable implements UserDetails {

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<@NotNull RoleGroup> roleGroups = new HashSet<>();

    private boolean enabled = false;


    public User(String username, String encodedPassword, RoleGroup roleGroups) {
        this.email = username;
        this.password = encodedPassword;
        this.roleGroups.add(roleGroups);
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleGroups.stream().flatMap(roleGroup ->
                RoleGroupEnum.getRoles(roleGroup.getName()).stream()).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public boolean isAdmin() {
        return roleGroups.stream().anyMatch(roleGroup -> roleGroup.getName().equals(ADMIN.name()));
    }

}

