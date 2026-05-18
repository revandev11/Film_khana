package org.my.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String content;
    @Column
    private double ratings;
    @Column
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie",nullable = false)
    private Movie movie;
    public Review(){

    }
    @ManyToOne()
    @JoinColumn(name = "user",nullable = false)
    public User user;





}
