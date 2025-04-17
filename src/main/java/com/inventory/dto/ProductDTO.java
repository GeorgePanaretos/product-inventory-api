package com.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Product name is mandatory")
    private String name;

    private String description;

    @PositiveOrZero(message = "Quantity cannot be negative")
    private int quantity;

    @PositiveOrZero(message = "Price cannot be negative")
    private BigDecimal price;
}