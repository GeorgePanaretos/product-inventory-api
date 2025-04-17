package com.inventory.controller;

import com.inventory.model.dto.ProductDTO;
import com.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products - Retrieves all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> list = productService.getAllProducts();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // POST /api/products - Creates a new product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.createProduct(productDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT /api/products/{id}/quantity?quantity=X - Updates product quantity
    @PutMapping("/{id}/quantity")
    public ResponseEntity<ProductDTO> updateProductQuantity(@PathVariable Long id,
                                                            @RequestParam("quantity") int quantity) {
        ProductDTO updated = productService.updateProductQuantity(id, quantity);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // GET /api/products/low-stock?threshold=5 - Retrieves products below a given stock threshold
    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductDTO>> getLowStockProducts(@RequestParam("threshold") int threshold) {
        List<ProductDTO> list = productService.getLowStockProducts(threshold);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
