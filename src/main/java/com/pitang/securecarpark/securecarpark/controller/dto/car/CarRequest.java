package com.pitang.securecarpark.securecarpark.controller.dto.car;


import com.pitang.securecarpark.securecarpark.entity.User;
import com.pitang.securecarpark.securecarpark.validation.LicensePlateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class CarRequest {

    @NotNull
    @Positive
    private int year;
    @LicensePlateFormat
    @NotBlank
    private String licensePlate;
    @NotBlank
    private String model;
    @NotBlank
    private String color;
}
