package org.my.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
@Column(nullable = false)
    private String title;
@Column(nullable = false)
    private String description;
@Column(nullable = false)
    private String genre;
@Column(nullable = false)
    private int relaeseYear;
public Movie(){}
@OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Review> reviews;

}
