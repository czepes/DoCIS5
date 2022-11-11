package ru.sfu.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

/**
 * User Persistence Entity
 * @author Agapchenko V.V.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Size(min=3, max=12, message="Not less than 3 and more than 12 symbols")
    private String username;

    @Size(min=3, message="Not less than 3 symbols")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    /**
     * Field for the Role id assigned during registration
     */
    @Transient
    private Integer regRole;

    /**
     * Default constructor
     */
    public User() {
        id = 0;
        regRole = 1;
    }

    /**
     * Get User authorities (roles)
     * @return User Roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    /**
     * Get User password
     * @return Password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Get Username
     * @return Username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Check if user account is expired
     * @return Check result
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Check if user account is locked
     * @return Check result
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Check if user credentials are expired
     * @return Check result
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Check if user account is enabled
     * @return Check result
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Integer getRegRole() {
        return regRole;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRole(Role role) {
        if (roles != null) this.roles.add(role);
    }

    public void setRegRole(Integer regRole) {
        this.regRole = regRole;
    }
}
