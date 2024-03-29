package com.pitang.securecarpark.securecarpark.controller;

import com.pitang.securecarpark.securecarpark.controller.dto.user.UserLogin;
import com.pitang.securecarpark.securecarpark.exception.ErrorResponse;
import com.pitang.securecarpark.securecarpark.security.jwt.JwtToken;
import com.pitang.securecarpark.securecarpark.security.jwt.JwtUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.ErrorManager;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn (@RequestBody UserLogin user, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = detailsService.getTokenAuthenticated(user.getLogin());
            return ResponseEntity.ok(token);
        }catch (AuthenticationException ex) {
            log.warn("Bad Credencials from login '{}'", user.getLogin());
        }
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Unauthorized - invalid credentials"));

    }
}
