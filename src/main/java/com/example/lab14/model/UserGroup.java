package com.example.lab14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_group")
@Setter
@Getter
public class UserGroup {
    @Id
    @SequenceGenerator(name = "user_groups_seq", sequenceName = "user_groups_sequence", allocationSize = 1)
    @GeneratedValue(generator = "user_groups_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;
    private String name;

    @ManyToMany(mappedBy = "userGroups")
    private Set<User> users;

    public UserGroup(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public UserGroup() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}