package com.pitang.securecarpark.securecarpark.controller.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLogin {

    @NotBlank
    private String login;

    @NotBlank
    @Size(min = 6, max = 8)
    private String password;
}
