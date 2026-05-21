package org.my.project.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponse {
    private String title;
    private String description;
    private String genre;
    private int relaeseYear;
public MovieResponse(){}

    public MovieResponse(String title, String description, String genre, int relaeseYear) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.relaeseYear = relaeseYear;
    }
}
