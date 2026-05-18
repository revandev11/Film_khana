package org.my.project.service;

import org.my.project.entity.Movie;
import org.my.project.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public List<Movie>getAllFilms(){
        return movieRepository.findAll();
    }





}
