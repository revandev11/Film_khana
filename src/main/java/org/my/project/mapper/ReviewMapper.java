package org.my.project.mapper;

import org.my.project.dto.request.ReviewCreateRequest;
import org.my.project.dto.response.ReviewResponse;
import org.my.project.entity.Review;

public class ReviewMapper {
public static Review toEntity(ReviewCreateRequest request){
    if (request==null) return null;

    Review review=new Review();
    review.setContent(request.getContent());
    review.setRatings(request.getRatings());
    review.setCreatedAt(request.getCreatedAt());
    return review;
}
public static ReviewResponse toDto(Review review){
    if (review==null) return null;

    ReviewResponse reviewResponse=new ReviewResponse();
    reviewResponse.setContent(review.getContent());
    reviewResponse.setRatings(review.getRatings());
    reviewResponse.setCreatedAt(review.getCreatedAt());
    return reviewResponse;
}
}
