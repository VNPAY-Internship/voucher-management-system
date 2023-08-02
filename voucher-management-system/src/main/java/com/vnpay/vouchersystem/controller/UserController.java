package com.vnpay.vouchersystem.controller;

import com.vnpay.vouchersystem.model.User;
import com.vnpay.vouchersystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/batch")
    public ResponseEntity<?> batchCreateUsers(@RequestBody List<User> users) {
        userService.saveAll(users);
        return ResponseEntity.ok("Batch users created successfully");
    }
}
