package org.example.ecommerce.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.ecommerce.Api.ApiResponse;
import org.example.ecommerce.Model.MerchantStock;
import org.example.ecommerce.Service.MerchantStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController
{
    private final MerchantStockService merchantStockService;

    @PostMapping("/add")
    public ResponseEntity<?> addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else {
            String result = merchantStockService.addMerchantStock(merchantStock);
            if (result != null) {
                return ResponseEntity.status(400).body(new ApiResponse(result));
            }else {
                return ResponseEntity.status(201).body(new ApiResponse("Merchant stock added successfully"));
            }
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getMerchantStock(){
        ArrayList<MerchantStock> result = merchantStockService.getMerchantStocks();
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("there are no merchant stocks yet"));
        }else {
            return ResponseEntity.status(200).body(result);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else{
            String result = merchantStockService.updateMerchantStock(id, merchantStock);
            if (result != null) {
                return ResponseEntity.status(400).body(new ApiResponse(result));
            }else {
                return ResponseEntity.status(200).body(new ApiResponse("Merchant stock updated successfully"));
            }
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeMerchantStock(@PathVariable String id){
        boolean result = merchantStockService.deleteMerchantStock(id);
        if (result) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant stock removed successfully"));
        }else {
            return ResponseEntity.status(404).body(new ApiResponse("Merchant stock not found"));
        }
    }


}
