package org.my.project.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewUpdateRequest {
    @NotBlank(message ="content is requered")
    private String content;
    @NotNull
    @Positive(message = "rate must me postivive")
    private double ratings;
    public ReviewUpdateRequest(){}

}
