package org.example.ecommerce.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product
{
    @NotEmpty(message = "Product ID is required")
    private String id;
    @NotEmpty(message = "Product name is required")
    @Size(min = 4, message = "the name should be longer than 3 characters")
    private String name;
    @NotNull
    @Min(value = 0, message = "Price shouldn't be negative number")
    private double price;
    @NotEmpty(message = "Category ID is required")
    private String categoryId;
}
