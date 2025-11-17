package org.example.ecommerce.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Api.ApiResponse;
import org.example.ecommerce.Model.Product;
import org.example.ecommerce.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product, Errors error){
        if (error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else {
            boolean result = productService.addProduct(product);
            if (result){
                return ResponseEntity.status(201).body(new ApiResponse("Product added successfully"));
            }else {
                return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
            }
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllProducts() {
        ArrayList<Product> result = productService.getProducts();
        if(result.isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("there are no product added yet"));
        }else {
            return ResponseEntity.status(200).body(result);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else {
            String result = productService.updateProduct(id, product);
            if (result != null){
                return ResponseEntity.status(400).body(new ApiResponse(result));
            }else {
                return ResponseEntity.status(200).body(new ApiResponse("Product updated successfully"));
            }
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable String id) {
        boolean result = productService.deleteProduct(id);
        if (result) {
            return ResponseEntity.status(200).body(new ApiResponse("Product removed successfully"));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
        }
    }

    @GetMapping("/get/review/{id}")
    public ResponseEntity<?> getReview(@PathVariable String id) {
        ArrayList result = productService.reviewsOfProduct(id);
        if(result.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no one reviewed this product yet"));
        }else {
            return ResponseEntity.status(200).body(result);
        }
    }

    @GetMapping("/get/within/{price}")
    public ResponseEntity<?> getWithin(@PathVariable double price) {
        ArrayList<Product> result = productService.getProductsUnderPrice(price);

        if (result.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no products within this range of price"));
        }else {
            return ResponseEntity.status(200).body(result);
        }
    }
}
