package com.Ecommerce.App.Repository;

import com.Ecommerce.App.Model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p  WHERE p.name like %:prduct%")
    public List<Product> findByName(@Param("prduct") String name);

    @Query("SELECT p FROM Product p  WHERE p.category like %:cat%")
    public List<Product> getProductCategoryName(@Param("cat") String category);

    List<Product> findAllByNameContainingIgnoreCase(String keyword, Sort sort);
}
