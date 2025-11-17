package org.example.ecommerce.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ecommerce.Api.ApiResponse;
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

    }
}
