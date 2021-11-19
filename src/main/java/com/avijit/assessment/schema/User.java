package com.avijit.assessment.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৬/১১/২১
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true)
    @Size(min = 2, max = 100, message = "Username character must be between 2 to 100!")
    private String username;

    private String firstName;

    private String lastName;

    @NotNull
    @Email
    private String email;

    private String phone;

    @Size(min = 6)
    @NotNull
    @JsonIgnore
    private String password;

    private int maximumBookAccess;


    public String getUsername() {
        return username;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            System.out.println("Authority for " + getUsername() + " -> " + authority.toString());
            authorities.add(authority);
        }
        return authorities;
    }

    public String getPassword() {
        return password;
    }
}
