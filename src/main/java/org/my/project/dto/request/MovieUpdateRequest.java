package org.my.project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MovieUpdateRequest {
    @NotBlank(message = "title is requered ")
    private String title;
    @NotBlank(message ="description is requered " )
    private String description;
    @NotBlank(message ="genre is requered " )
    private String genre;
    @NotNull(message ="relaese year is requered ")
    @Positive(message = "Relaese year must be positive")
    private int relaeseYear;
    public MovieUpdateRequest(){}
}
