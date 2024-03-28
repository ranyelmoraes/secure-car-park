package com.pitang.securecarpark.securecarpark.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pitang.securecarpark.securecarpark.validation.LicensePlateFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="manufacture_year")
    private int year;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;
    private String model;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User userId;

}
