package org.my.project.dto.Request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ReviewCreateRequest {
    @NotBlank(message ="content is requered")
    private String content;
    @NotNull
    @Positive(message = "rate must me postivive")
    private double ratings;
    @Future
    private LocalDateTime createdAt;
    public ReviewCreateRequest(){}
}
