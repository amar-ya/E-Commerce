package org.example.ecommerce.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category
{
    @NotEmpty(message = "Category ID is required")
    private String id;
    @NotEmpty(message = "Category name is required")
    @Size(min = 4, message = "the name should be longer than 3 characters")
    private String name;
}
