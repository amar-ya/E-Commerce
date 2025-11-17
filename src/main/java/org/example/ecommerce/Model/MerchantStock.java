package org.example.ecommerce.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class MerchantStock
{
    @NotEmpty(message = "Merchant Stock ID is required")
    private String id;
    @NotEmpty(message = "Product ID is required")
    private String productId;
    @NotEmpty(message = "Merchant ID is required")
    private String merchantId;
    @NotNull(message = "Stock is required")
    @Min(value = 10, message = "Stock should be at least 10")
    private Integer stock;
}
