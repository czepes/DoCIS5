package ru.sfu.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Role Persistence Entity
 * @author Agapchenko V.V.
 */
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /**
     * Default constructor
     */
    public Role() {}

    /**
     * Constructor with id
     * @param id Role Identification Number
     */
    public Role(Integer id) {
        this.id = id;
    }

    /**
     * Constructor with id and name
     * @param id Role Identification Number
     * @param name Role Name
     */
    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get Role's authority
     * @return
     */
    @Override
    public String getAuthority() {
        return getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
