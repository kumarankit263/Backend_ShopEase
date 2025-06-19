package com.Ecommerce.App.Service;

import com.Ecommerce.App.Exception.ReviewException;
import com.Ecommerce.App.Model.Review;
import jakarta.validation.Valid;

import java.util.List;

public interface ReviewService {
    public Review addReviewToProduct(Integer productId, Integer userId, Review review)  throws ReviewException;

    public Review updateReviewToProduct(Integer reviewId,  Review review)  throws ReviewException;

    public void deleteReview(Integer reviewId) throws ReviewException;

    public List<Review> getAllReviewOfProduct(Integer productId) throws ReviewException;
}
