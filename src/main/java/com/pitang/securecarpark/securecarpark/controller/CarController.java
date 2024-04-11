package com.pitang.securecarpark.securecarpark.controller;

import com.pitang.securecarpark.securecarpark.controller.dto.car.CarRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.car.CarResponse;
import com.pitang.securecarpark.securecarpark.controller.dto.mapper.CarMapper;
import com.pitang.securecarpark.securecarpark.entity.Car;
import com.pitang.securecarpark.securecarpark.entity.User;
import com.pitang.securecarpark.securecarpark.exception.ErrorResponse;
import com.pitang.securecarpark.securecarpark.service.CarService;
import com.pitang.securecarpark.securecarpark.service.UserService;
import com.pitang.securecarpark.securecarpark.utils.UserDetailsUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name = "Cars", description = "Cars CRUD endpoints")
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @Operation(summary = "Get All Cars", security = @SecurityRequirement(name = "security"),
            responses = {
            @ApiResponse(
                    responseCode = "200", description = "Sucess",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarResponse.class)))
    })
    @GetMapping
    public  ResponseEntity<List<CarResponse>> getAllCars() {
        String username = UserDetailsUtil.getCurrentUsername();
        User user = userService.findByLogin(username);
        List<Car> cars = user.getCars();
        return ResponseEntity.ok(CarMapper.toList(cars));
    }

    @Operation(summary = "Find car by ID",security = @SecurityRequirement(name = "security"),
            responses = {
            @ApiResponse(
                    responseCode = "200", description = "Car Found Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarResponse.class))),

            @ApiResponse(
                    responseCode = "404", description = "Car Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        String username = UserDetailsUtil.getCurrentUsername();
        User user = userService.findByLogin(username);
        Optional<Car> optionalCar = user.getCars().stream().filter(car -> car.getId().equals(id)).findFirst();
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            return ResponseEntity.ok(CarMapper.carToDto(car));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create Car", security = @SecurityRequirement(name = "security"),
            responses = {
            @ApiResponse(
                    responseCode = "201", description = "Created Sucess",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarResponse.class))),

            @ApiResponse(
                    responseCode = "409", description = "Car already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(
                    responseCode = "422", description = "Invalid or Missing Fields",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
    })
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping
    public ResponseEntity<CarResponse> create(@Valid @RequestBody CarRequest car) {
            String username = UserDetailsUtil.getCurrentUsername();
            User user = userService.findByLogin(username);
            Car c = CarMapper.dtoToCar(car);
            c.setUserId(user);
            Car newCar = carService.saveCar(c);
            return ResponseEntity.status(HttpStatus.CREATED).body(CarMapper.carToDto(newCar));

    }

    @Operation(summary = "Update car by ID",security = @SecurityRequirement(name = "security"),
            responses = {
            @ApiResponse(
                    responseCode = "200", description = "Car Updated Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarResponse.class))),

            @ApiResponse(
                    responseCode = "404", description = "Car Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "422", description = "Invalid or Missing Fields",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateUser(@PathVariable Long id, @Valid @RequestBody CarRequest updatedCarData) {
        Car car = carService.getCarById(id);
        Car updatedCar = carService.updateCar(car, CarMapper.dtoToCar(updatedCarData));
        return ResponseEntity.ok(CarMapper.carToDto(updatedCar));
    }

    @Operation(summary = "Delete Car by ID", security = @SecurityRequirement(name = "security"),
            responses = {
            @ApiResponse(
                    responseCode = "204", description = "Car Deleted Successfully",
                    content = @Content(mediaType = "application/json")),

            @ApiResponse(
                    responseCode = "404", description = "Car Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarByID(@PathVariable Long id) {
        return carService.deleteCarById(id);
    }

}