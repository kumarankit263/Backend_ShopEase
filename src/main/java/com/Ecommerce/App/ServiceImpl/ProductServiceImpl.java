package com.Ecommerce.App.ServiceImpl;


import com.Ecommerce.App.Exception.ProductException;
import com.Ecommerce.App.Model.Product;
import com.Ecommerce.App.ModelDTO.ProductDTO;
import com.Ecommerce.App.Repository.ProductRepository;
import com.Ecommerce.App.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) throws ProductException {
        if (product == null)
            throw new ProductException("Product Can not be Null");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Integer productId, ProductDTO product) throws ProductException {
        Optional<Product> products = productRepository.findById(productId);
        if (products.isEmpty()) {
            throw new ProductException("Product with ID " + productId + " not found.");
        }
        Product existingProduct = products.get();

        // Update the existing product's properties with the new data
        System.out.println("before");
        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setDescription(product.getDescription());
        System.out.println("after");
        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public List<Product> getProductByName(String name) throws ProductException {
        List<Product> existProductByName = productRepository.findByName(name);
        if (existProductByName.isEmpty()) {
            throw new ProductException("Product Not found with name " + name);
        }
        return existProductByName;
    }

    @Override
    public List<Product> getAllProduct(String keyword, String sortDirection, String sortBy) throws ProductException {
        return List.of();
    }

    @Override
    public List<Product> getProductByCategory(String category) throws ProductException {
        // Retrieve products by category from the database
        List<Product> allproductCategoryName = productRepository.getProductCategoryName(category);
        if (allproductCategoryName.isEmpty())
            throw new ProductException("Product with category Name " + category + " not found.");

        return allproductCategoryName;
    }

    @Override
    public void removeProduct(Integer productId) throws ProductException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Product with ID " + productId + " not found."));

        productRepository.delete(existingProduct);
    }

    @Override
    public Product getSingleProduct(Integer productId) {
        Product single = productRepository.findById(productId).orElseThrow(() -> new ProductException("Product not found"));
        return single;
    }
}
