package com.Ecommerce.App.ServiceImpl;

import com.Ecommerce.App.Exception.ReviewException;
import com.Ecommerce.App.Model.Product;
import com.Ecommerce.App.Model.Review;
import com.Ecommerce.App.Model.User;
import com.Ecommerce.App.Repository.ProductRepository;
import com.Ecommerce.App.Repository.ReviewRepository;
import com.Ecommerce.App.Repository.UserRepository;
import com.Ecommerce.App.Service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ProductRepository productRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    public ReviewServiceImpl(ProductRepository productRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review addReviewToProduct(Integer productId, Integer userId, Review review) throws ReviewException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ReviewException("Product Not Found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ReviewException("User Not Found In Database"));

        review.setUser(user);
        review.setProduct(product);
        review.setCreatedAt(LocalDateTime.now()); // ⬅ Set timestamp here
        user.getReviews().add(review);
        product.getReviews().add(review);

        return reviewRepository.save(review);
    }

    @Override
    public Review updateReviewToProduct(Integer reviewId, Review review) throws ReviewException {
        Review existingReview =reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException("Review With Id "+reviewId+"Not Found In DataBase"));

        existingReview.setComment(review.getComment());
        existingReview.setRating(review.getRating());
        return existingReview;
    }

    @Override
    public void deleteReview(Integer reviewId) throws ReviewException {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException("Review With Id "+reviewId+"Not Found In DataBase"));

        reviewRepository.delete(existingReview);
    }

    @Override
    public List<Review> getAllReviewOfProduct(Integer productId) throws ReviewException {
        Product existingProduct=productRepository.findById(productId)
                .orElseThrow(()->new ReviewException("Invalid Product id"));

        List<Review> allReviewsByProduct=reviewRepository.findAllReviewsByProductId(productId);
        if(allReviewsByProduct.isEmpty()) {
            throw new ReviewException ("No Rewiew Of Given Product is Available");
        }
        return allReviewsByProduct;
    }
}
