package com.example.lab14.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @javax.persistence.Id
    @Id
    @SequenceGenerator(name = "users_seq", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_groups",
            joinColumns =@JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"
            ))
    private Set<UserGroup> userGroups = new HashSet<>();

    public void addUserGroups(UserGroup group){
        userGroups.add(group);
        group.getUsers().add(this);
    }

    public void removeUserGroups(UserGroup group){
        userGroups.remove(group);
        group.getUsers().remove(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}