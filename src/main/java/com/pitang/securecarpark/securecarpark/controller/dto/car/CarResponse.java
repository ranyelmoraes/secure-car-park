package com.pitang.securecarpark.securecarpark.controller.dto.car;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class CarResponse {

    private Long id;
    private int year;
    private String licensePlate;
    private String model;
    private String color;

}
