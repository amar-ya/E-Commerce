package org.example.ecommerce.Controller;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Api.ApiResponse;
import org.example.ecommerce.Model.Merchant;
import org.example.ecommerce.Service.MerchantService;
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

    @PostMapping("/add")
    public ResponseEntity<?> addMerchant(@RequestBody Merchant merchant, Errors error){
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
    public ResponseEntity<?> updateMerchant(@PathVariable String id, @RequestBody Merchant merchant, Errors error){
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
}
