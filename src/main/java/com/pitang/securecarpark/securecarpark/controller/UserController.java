package com.pitang.securecarpark.securecarpark.controller;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserResponse;
import com.pitang.securecarpark.securecarpark.controller.dto.mapper.UserMapper;
import com.pitang.securecarpark.securecarpark.entity.User;
import com.pitang.securecarpark.securecarpark.exception.ErrorResponse;
import com.pitang.securecarpark.securecarpark.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Users CRUD endpoints")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get All Users", responses = {
            @ApiResponse(
                    responseCode = "200", description = "Sucess",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List <User> users = userService.getAll();
        return ResponseEntity.ok(UserMapper.toList(users));
    }

    @Operation(summary = "Create User", responses = {
            @ApiResponse(
                    responseCode = "201", description = "Created Sucess",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponse.class))),

            @ApiResponse(
                    responseCode = "409", description = "User already exists",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(
                    responseCode = "422", description = "Invalid or Missing Fields",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userDto) {
        User user = userService.save(UserMapper.dtoToUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.userToDto(user));
    }

    @Operation(summary = "Find user by ID", responses = {
            @ApiResponse(
                    responseCode = "200", description = "User Found Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),

            @ApiResponse(
                    responseCode = "404", description = "User Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.userToDto(user));
    }

    @Operation(summary = "Delete user by ID", responses = {
            @ApiResponse(
                    responseCode = "204", description = "User Deleted Successfully",
                    content = @Content(mediaType = "application/json")),

            @ApiResponse(
                    responseCode = "404", description = "User Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return userService.deleteUser(id);
    }

    @Operation(summary = "Update user by ID", responses = {
            @ApiResponse(
                    responseCode = "200", description = "User Updated Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),

            @ApiResponse(
                    responseCode = "404", description = "User Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "422", description = "Invalid or Missing Fields",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest updatedUserData) {
        User user = userService.getUserById(id);
        User updatedUser = userService.updateUser(user, UserMapper.dtoToUser(updatedUserData));
        return ResponseEntity.ok(UserMapper.userToDto(updatedUser));
    }
}