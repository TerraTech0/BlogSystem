package com.example.blogsystem.Controller;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.addAllUsers(user);
        return ResponseEntity.ok().body(new ApiResponse("user added successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.ok().body(message);
        }
        userService.updateUser(id, user);
        return ResponseEntity.ok().body("user updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("user deleted successfully!");
    }

    @PostMapping("/check-vaild-user/{username}/{password}")
    public ResponseEntity checkValidUser(@PathVariable String username, @PathVariable String password){
        return ResponseEntity.ok().body("user valid \n"+ userService.checkValidUser(username, password) );
    }

    @GetMapping("/find-user-by-email/{email}")
    public ResponseEntity findUserByEmail(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/find-user-by-username/{username}")
    public ResponseEntity findUserByUsername(@PathVariable String username){
        User user = userService.findUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/find-user-by-register-date/{date}")
    public ResponseEntity findUserByRegisterDate(@PathVariable Date date){
        return ResponseEntity.ok().body(userService.findUserByRegisterDate(date));
    }

}
