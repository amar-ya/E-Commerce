package org.example.ecommerce.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Api.ApiResponse;
import org.example.ecommerce.Model.Merchant;
import org.example.ecommerce.Model.MerchantStock;
import org.example.ecommerce.Service.MerchantService;
import org.example.ecommerce.Service.MerchantStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController
{
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;

    @PostMapping("/add")
    public ResponseEntity<?> addMerchant(@RequestBody @Valid Merchant merchant, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else {
            merchantService.addMerchant(merchant);
            return ResponseEntity.ok(new ApiResponse("Merchant added successfully"));
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllMerchants() {
        ArrayList<Merchant> result = merchantService.getMerchants();
        if (result.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there are no registered merchants yet"));
        }else {
            return ResponseEntity.status(200).body(result);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant, Errors error){
        if (error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else {
            boolean isUpdated = merchantService.updateMerchant(id, merchant);
            if (isUpdated){
                return ResponseEntity.ok(new ApiResponse("Merchant updated successfully"));
            }else {
                return ResponseEntity.status(400).body(new ApiResponse("Merchant not found"));
            }
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeMerchant(@PathVariable String id){
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted){
            return ResponseEntity.ok(new ApiResponse("Merchant removed successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("Merchant does not exist"));
        }
    }

    @PostMapping("/add/merchant-stock/{mid}/{pid}/{stock}")
    public ResponseEntity<?> addMerchantStock(@PathVariable String mid,@PathVariable String pid,@PathVariable int stock){
        String result = merchantService.addMerchantStockItems(mid, pid, stock);
        if (result!=null){
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }else {
            return ResponseEntity.status(200).body(new ApiResponse("merchant stock updated successfully"));
        }
    }



}
