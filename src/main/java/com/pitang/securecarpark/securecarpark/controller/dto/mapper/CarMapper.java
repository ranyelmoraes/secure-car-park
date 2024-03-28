package com.pitang.securecarpark.securecarpark.controller.dto.mapper;

import com.pitang.securecarpark.securecarpark.controller.dto.car.CarRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.car.CarResponse;
import com.pitang.securecarpark.securecarpark.entity.Car;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {

    public static Car dtoToCar(CarRequest createCarDto) {
        return new ModelMapper().map(createCarDto, Car.class);
    }
    public static CarResponse carToDto(Car car) {
        return new ModelMapper().map(car, CarResponse.class);
    }
    public static List<CarResponse> toList(List<Car> cars) {
        return cars.stream().map(CarMapper::carToDto).collect(Collectors.toList());
    }

}
