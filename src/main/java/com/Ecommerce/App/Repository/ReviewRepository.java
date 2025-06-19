package com.Ecommerce.App.Repository;

import com.Ecommerce.App.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
    List<Review> findAllReviewsByProductId(@Param("productId") Integer productId);

}
