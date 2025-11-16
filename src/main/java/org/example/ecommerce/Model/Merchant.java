package org.example.ecommerce.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant
{
    @NotEmpty(message = "Merchant ID is required")
    private String id;
    @NotEmpty(message = "Merchant name is required")
    @Size(min = 4, message = "the name should be longer than 3 characters")
    private String name;
}
