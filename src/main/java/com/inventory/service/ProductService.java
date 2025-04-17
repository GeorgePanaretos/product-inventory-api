package com.inventory.service;

import com.inventory.model.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProductQuantity(Long id, int quantity);
    List<ProductDTO> getLowStockProducts(int threshold);
    void deleteProduct(Long id);
}
