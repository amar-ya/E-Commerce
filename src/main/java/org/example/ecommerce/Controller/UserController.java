package org.example.ecommerce.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Api.ApiResponse;
import org.example.ecommerce.Model.Product;
import org.example.ecommerce.Model.User;
import org.example.ecommerce.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else {
            userService.addUser(user);
            return ResponseEntity.status(201).body(new ApiResponse("User added successfully"));
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllUser(){
        ArrayList<User> result = userService.getUsers();
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No registered users yet"));
        }else {
            return ResponseEntity.status(200).body(result);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id,@RequestBody @Valid User user,Errors errors ){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }else {
            boolean result = userService.updateUser(id, user);
            if (result) {
                return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
            }else {
                return ResponseEntity.status(404).body(new ApiResponse("user not found"));
            }
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeUser(@PathVariable String id){
        boolean result = userService.deleteUser(id);
        if (result) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }else {
            return ResponseEntity.status(404).body(new ApiResponse("User not found"));
        }
    }

    @PutMapping("/buy/{uid}/{pid}/{mid}")
    public ResponseEntity<?> buy(@PathVariable String uid, @PathVariable String pid, @PathVariable String mid){
        String result = userService.buy(uid,pid,mid);
        if(result!=null) {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }else {
            return ResponseEntity.status(200).body(new ApiResponse("purchase successfully"));
        }
    }

    @PutMapping("/charge-balance/{id}/{amount}")
    public ResponseEntity<?> chargeBalance(@PathVariable String id, @PathVariable int amount){
        boolean result = userService.chargeBalance(id, amount);
        if (result) {
            return ResponseEntity.status(200).body(new ApiResponse("purchase successfully"));
        }else {
            return ResponseEntity.status(404).body(new ApiResponse("user not found"));
        }
    }

    @GetMapping("/get/history/{id}")
    public ResponseEntity<?> getHistory(@PathVariable String id){
        ArrayList result = userService.getHistoryById(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("this user doesnt have purchase history yet"));
        }else {
            return ResponseEntity.status(200).body(result);
        }
    }

    @PutMapping("/gift-balance/{senderId}/{receiverId}/{amount}")
    public ResponseEntity<?> gift(@PathVariable String senderId, @PathVariable String receiverId,@PathVariable Integer amount){
        String result = userService.giftBalance(senderId, receiverId, amount);
        if (result!=null) {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }else {
            return ResponseEntity.status(200).body(new ApiResponse("balance transferred successfully"));
        }
    }

    @PostMapping("/selling-apply/{id}")
    private ResponseEntity<?> sellingAproval(@PathVariable String id){
        String result = userService.SellingLicence(id);
        if(result != null) {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }else {
            return ResponseEntity.status(200).body(new ApiResponse("licence approved successfully"));
        }
    }

    @PostMapping("/review/{userId}/{productId}/{review}")
    public ResponseEntity<?> review(@PathVariable String userId, @PathVariable String productId, @PathVariable String review){
        String result = userService.reviewProduct(userId, productId, review);
        if (result!=null) {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }else {
            return ResponseEntity.status(200).body(new ApiResponse("product review posted successfully"));
        }
    }

    @PutMapping("/buy/{uid}/{pid}/{mid}/{amount}")
    public ResponseEntity<?> buy(@PathVariable String uid, @PathVariable String pid, @PathVariable String mid,@PathVariable int amount){
        String result = userService.buy(uid,pid,mid,amount);
        if(result!=null) {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }else {
            return ResponseEntity.status(200).body(new ApiResponse("purchase successfully"));
        }
    }


}
