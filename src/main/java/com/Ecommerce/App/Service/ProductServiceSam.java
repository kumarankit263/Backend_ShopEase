package com.Ecommerce.App.Service;

import com.Ecommerce.App.Exception.ShipperException;
import com.Ecommerce.App.Model.OrderItem;
import com.Ecommerce.App.Model.Product;
import com.Ecommerce.App.Model.Review;
import com.Ecommerce.App.Model.Shipper;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceSam {


    public static List<Product> getProducts() {
        //create sample product1
        Product product1 = new Product();
        product1.setProductId(1);
        product1.setName("Iphone 16");
        product1.setImageUrl("https://images.pexels.com/photos/11120516/pexels-photo-11120516.jpeg?auto=compress&cs=tinysrgb&w=600");
        product1.setAvailable(true);
        product1.setDescription("Latest smartPhone with advanced features");
        product1.setPrice(499.99);
        product1.setCategory("Electronics");

        //create OrderItems for Product1

        OrderItem orderItem1=new OrderItem();
        orderItem1.setProduct(product1);
        orderItem1.setOrderId(101);
        orderItem1.setQuantity(2);

        OrderItem orderItem2=new OrderItem();
        orderItem2.setProduct(product1);
        orderItem2.setOrderId(102);
        orderItem2.setQuantity(1);

        // Add OrderItems to Product
        List<OrderItem>orderItems1=new ArrayList<>();
        orderItems1.add(orderItem1);
        orderItems1.add(orderItem2);
        product1.setOrderItem(orderItems1);

        //Create Review for Product 1
        Review review1=new Review();
        review1.setProduct(product1);
        //review1.setReviewText("Excellent product, highly recommend!");
        review1.setRating(5);

        Review review2 = new Review();
        review2.setProduct(product1);
        //review2.setReviewText("Good value for money, but battery life could be better.");
        review2.setRating(4);

        // Add Reviews to Product
        List<Review> reviews1 = new ArrayList<>();
        reviews1.add(review1);
        reviews1.add(review2);
        product1.setReviews(reviews1);

        // Create another Product
        Product product2 = new Product();
        product2.setProductId(2);
        product2.setName("MAC");
        product2.setImageUrl("https://images.pexels.com/photos/18311089/pexels-photo-18311089/free-photo-of-laptop-iphone-and-headphones.jpeg?auto=compress&cs=tinysrgb&w=600");
        product2.setAvailable(true);
        product2.setDescription("High performance laptop for work and play");
        product2.setPrice(999.99);
        product2.setCategory("Electronics");

        // Create OrderItems for Product 2
        OrderItem orderItem3 = new OrderItem();
        orderItem3.setProduct(product2);
        orderItem3.setOrderId(103);
        orderItem3.setQuantity(1);

        // Add OrderItems to Product 2
        List<OrderItem> orderItems2 = new ArrayList<>();
        orderItems2.add(orderItem3);
        product2.setOrderItem(orderItems2);

        // Create Reviews for Product 2
        Review review3 = new Review();
        review3.setProduct(product2);
        // review3.setReviewText("Great laptop, very fast and reliable.");
        review3.setRating(5);

        // Add Reviews to Product 2
        List<Review> reviews2 = new ArrayList<>();
        reviews2.add(review3);
        product2.setReviews(reviews2);

        // Create a list to store all Products
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        // Return the list of products
        return products;

    }

}
