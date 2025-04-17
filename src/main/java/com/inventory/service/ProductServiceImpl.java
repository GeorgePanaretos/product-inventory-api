package com.inventory.service;

import com.inventory.model.dto.ProductDTO;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return mapToDTO(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(" " + id))
        );
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        Product saved = productRepository.save(product);
        return mapToDTO(saved);
    }

    @Override
    public ProductDTO updateProductQuantity(Long id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        product.setQuantity(quantity);
        Product updated = productRepository.save(product);
        return mapToDTO(updated);
    }

    @Override
    public List<ProductDTO> getLowStockProducts(int threshold) {
        return productRepository.findByQuantityLessThan(threshold)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productRepository.delete(product);
    }

    private ProductDTO mapToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(),
                product.getDescription(), product.getQuantity(),
                product.getPrice());
    }
}
