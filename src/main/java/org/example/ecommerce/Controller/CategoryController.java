package org.example.ecommerce.Controller;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Api.ApiResponse;
import org.example.ecommerce.Model.Category;
import org.example.ecommerce.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1/category")
@RequiredArgsConstructor
public class CategoryController
{
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return ResponseEntity.status(201).body(new ApiResponse("Category added successfully"));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllCategories(){
        ArrayList<Category> result = categoryService.getCategories();
        if (result.isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("the admin have not added any category yet"));
        }else {
            return ResponseEntity.status(200).body(result);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody Category category, Errors error){
        if (error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else {
            boolean result = categoryService.updateCategory(id, category);
            if (result){
                return ResponseEntity.status(200).body(new ApiResponse("Category updated successfully"));
            }else {
                return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
            }
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable String id){
        boolean result = categoryService.deleteCategory(id);
        if (result){
            return ResponseEntity.status(200).body(new ApiResponse("Category removed successfully"));
        }else {
            return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
        }
    }
}
