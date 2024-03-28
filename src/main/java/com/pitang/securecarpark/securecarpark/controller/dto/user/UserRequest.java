package com.pitang.securecarpark.securecarpark.controller.dto.user;

import com.pitang.securecarpark.securecarpark.entity.Car;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;
    private Date birthday;

    @NotBlank
    private String login;

    @NotBlank
    @Size(min = 6, max = 8)
    private String password;
    @NotBlank
    private String phone;
    private List<Car> cars;
}
