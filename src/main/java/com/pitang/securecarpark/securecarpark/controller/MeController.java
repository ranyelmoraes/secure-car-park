package com.pitang.securecarpark.securecarpark.controller;

import com.pitang.securecarpark.securecarpark.controller.dto.mapper.UserMapper;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserResponse;
import com.pitang.securecarpark.securecarpark.entity.User;
import com.pitang.securecarpark.securecarpark.exception.EntityNotFoundException;
import com.pitang.securecarpark.securecarpark.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class MeController {

    @Autowired
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getLoggedInUser() {

        UserDetails loggedInUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findByLogin(loggedInUser.getUsername());

        UserResponse userResponse = UserMapper.userToDto(user);
        return ResponseEntity.ok(userResponse);
    }
}
