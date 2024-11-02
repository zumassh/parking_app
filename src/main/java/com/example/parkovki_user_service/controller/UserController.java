package com.example.parkovki_user_service.controller;

import com.example.parkovki_user_service.entity.UserEntity;
import com.example.parkovki_user_service.exception.UserAlreadyExistException;
import com.example.parkovki_user_service.exception.UserNotFoundException;
import com.example.parkovki_user_service.repository.UserRepo;
import com.example.parkovki_user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
            return ResponseEntity.ok("Пользователь успешно сохранен.");
        }
        catch (UserAlreadyExistException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getUserByPhoneNumber(@RequestParam String phoneNumber){
        try {
            return ResponseEntity.ok(userService.getByPhone(phoneNumber));
        }
        catch (UserNotFoundException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity deleteUserByPhoneNumber(@PathVariable String phoneNumber){
        try {
            return ResponseEntity.ok(userService.deleteUser(phoneNumber));
        }
        catch (UserNotFoundException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity getCarsByUserId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getCarsByUserId(id));
        }
        catch (UserNotFoundException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }
}
