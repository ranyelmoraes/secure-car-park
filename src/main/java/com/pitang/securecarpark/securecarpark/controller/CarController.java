package com.pitang.securecarpark.securecarpark.controller;

import com.pitang.securecarpark.securecarpark.controller.dto.car.CarRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.car.CarResponse;
import com.pitang.securecarpark.securecarpark.controller.dto.mapper.CarMapper;
import com.pitang.securecarpark.securecarpark.controller.dto.mapper.UserMapper;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserResponse;
import com.pitang.securecarpark.securecarpark.entity.Car;
import com.pitang.securecarpark.securecarpark.entity.User;
import com.pitang.securecarpark.securecarpark.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public  ResponseEntity<List<CarResponse>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(CarMapper.toList(cars));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(CarMapper.carToDto(car));
    }

    @PostMapping
    public ResponseEntity<CarResponse> create(@Valid @RequestBody CarRequest car) {
        Car newCar = carService.saveCar(CarMapper.dtoToCar(car));
        return ResponseEntity.status(HttpStatus.CREATED).body(CarMapper.carToDto(newCar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateUser(@PathVariable Long id, @Valid @RequestBody CarRequest updatedCarData) {
        Car car = carService.getCarById(id);
        Car updatedCar = carService.updateCar(car, CarMapper.dtoToCar(updatedCarData));
        return ResponseEntity.ok(CarMapper.carToDto(updatedCar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarByID(@PathVariable Long id) {
        return carService.deleteCarById(id);
    }

}