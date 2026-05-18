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
    public Long id;
    @Column
    public String Content;
    @Column
    public double ratings;
    @Column
    public LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie",nullable = false)
    public Movie movie;
    public Review(){

    }
    @ManyToOne()
    @JoinColumn(name = "user",nullable = false)
    public User user;





}
