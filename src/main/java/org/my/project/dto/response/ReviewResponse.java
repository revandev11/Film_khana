package org.my.project.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ReviewResponse {
    private String content;
    private double ratings;
    private LocalDateTime createdAt;
    public ReviewResponse (){}

}
