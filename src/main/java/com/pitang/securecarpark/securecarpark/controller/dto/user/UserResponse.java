package com.pitang.securecarpark.securecarpark.controller.dto.user;

import com.pitang.securecarpark.securecarpark.entity.Car;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private String login;
    private String password;
    private String phone;
    private List<Car> cars;
}
