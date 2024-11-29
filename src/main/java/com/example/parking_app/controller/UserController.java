package com.example.parking_app.controller;

import com.example.parking_app.entity.UserEntity;
import com.example.parking_app.exception.UserAlreadyExistException;
import com.example.parking_app.exception.UserNotFoundException;
import com.example.parking_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user) {
        try {
            UserEntity loggedInUser = userService.login(user.getPhoneNumber(), user.getPassword());
            return ResponseEntity.ok(Map.of(
                    "id", loggedInUser.getId(),
                    "message", "Успешный вход. Сессия создана"
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
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

    @PostMapping("/{id}/wallet")
    public ResponseEntity addMoneyToWallet(@RequestParam String phoneNumber, @RequestParam Integer money, @PathVariable String id){
        try {
            return ResponseEntity.ok(userService.addMoneyToWallet(phoneNumber, money));
        }
        catch (UserNotFoundException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }
}
