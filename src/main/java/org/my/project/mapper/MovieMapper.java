package org.my.project.mapper;

import org.my.project.dto.request.MovieCreateRequest;
import org.my.project.dto.response.MovieResponse;
import org.my.project.entity.Movie;

public class MovieMapper {
    public static Movie toEntity(MovieCreateRequest request){
        if (request==null) return  null;
        Movie movie=new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setGenre(request.getGenre());
        movie.setRelaeseYear(request.getRelaeseYear());
        return movie;
    }
    public static MovieResponse toDto(Movie movie){
        if (movie==null) return null;

        MovieResponse movieResponse=new MovieResponse();
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setDescription(movie.getDescription());
        movieResponse.setGenre(movie.getGenre());
        movieResponse.setRelaeseYear(movie.getRelaeseYear());
        return movieResponse;

    }




}
