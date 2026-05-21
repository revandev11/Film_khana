package org.my.project.service;

import jakarta.transaction.Transactional;
import org.my.project.dto.request.MovieUpdateRequest;
import org.my.project.dto.response.MovieResponse;
import org.my.project.entity.Movie;
import org.my.project.exception.ResourceNotFoundException;
import org.my.project.mapper.MovieMapper;
import org.my.project.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponse>getAllMovies(){
    List<Movie>movies=movieRepository.findAll();
    return movies.stream()
            .map(MovieMapper::toDto)
            .toList();
    }
    public MovieResponse getMoviesById(Long id){
        Movie movie=movieRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("the movie is not found"));
        return  new MovieResponse(movie.getTitle(),movie.getGenre(),movie.getDescription(),movie.getRelaeseYear());
    }
    @Transactional
    public void deleteMovie(Long id){
        if (!movieRepository.existsById(id))
            throw new ResourceNotFoundException("this movie is not found");
        movieRepository.findById(id);
    }
    @Transactional
    public MovieResponse updateMovie(Long id, MovieUpdateRequest request){
        Movie movie=movieRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("the movie is not found"));
       movie.setDescription(request.getDescription());
       movie.setRelaeseYear(request.getRelaeseYear());
       movie.setGenre(request.getGenre());
       movie.setTitle(request.getTitle());
        Movie save = movieRepository.save(movie);
        return MovieMapper.toDto(save);
    }





}
