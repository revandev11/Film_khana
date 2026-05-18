package org.my.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.my.project.enums.Roles;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_name")
    private String userName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Roles roles;

    public User(){}

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    public List<Review>reviews;

}
