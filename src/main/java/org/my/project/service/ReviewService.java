package org.my.project.service;

import jakarta.transaction.Transactional;
import org.my.project.dto.request.ReviewUpdateRequest;
import org.my.project.dto.response.ReviewResponse;
import org.my.project.entity.Review;
import org.my.project.exception.ResourceNotFoundException;
import org.my.project.mapper.ReviewMapper;
import org.my.project.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public List<ReviewResponse>getAllReviews(ReviewResponse reviewResponse){
        List<Review> all = reviewRepository.findAll();
        return all.stream().map(ReviewMapper::toDto).toList();
    }
    public ReviewResponse getReviewsByID(Long id){
        Review byId = reviewRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("this review is not found"));
        return ReviewMapper.toDto(byId);
    }
    @Transactional
    public void deleteById(Long id){
        if (!reviewRepository.existsById(id)){
            throw new ResourceNotFoundException("this review was not found");
        }
        reviewRepository.deleteById(id);
    }
    @Transactional
    public ReviewResponse updateReview( Long id,ReviewUpdateRequest reviewUpdateRequest){
        Review review=reviewRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("this review is not found"));
        review.setContent(reviewUpdateRequest.getContent());
        review.setRatings(reviewUpdateRequest.getRatings());
        reviewRepository.save(review);
        return ReviewMapper.toDto(review);
    }

}
