package com.pitang.securecarpark.securecarpark.controller;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserResponse;
import com.pitang.securecarpark.securecarpark.controller.dto.mapper.UserMapper;
import com.pitang.securecarpark.securecarpark.entity.User;
import com.pitang.securecarpark.securecarpark.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List <User> users = userService.getAll();
        return ResponseEntity.ok(UserMapper.toList(users));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userDto) {
        User user = userService.save(UserMapper.dtoToUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.userToDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.userToDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest updatedUserData) {
        User user = userService.getUserById(id);
        User updatedUser = userService.updateUser(user, UserMapper.dtoToUser(updatedUserData));
        return ResponseEntity.ok(UserMapper.userToDto(updatedUser));
    }
}