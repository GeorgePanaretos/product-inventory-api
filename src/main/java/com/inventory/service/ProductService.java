package com.inventory.service;

import com.inventory.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO deleteProduct(ProductDTO productDTO);
    ProductDTO updateProductQuantity(Long id, int quantity);
    List<ProductDTO> getLowStockProducts(int threshold);
}
