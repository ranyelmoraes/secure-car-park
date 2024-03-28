package com.pitang.securecarpark.securecarpark.service;

import com.pitang.securecarpark.securecarpark.entity.Car;
import com.pitang.securecarpark.securecarpark.exception.EmailUniqueViolationException;
import com.pitang.securecarpark.securecarpark.exception.EntityNotFoundException;
import com.pitang.securecarpark.securecarpark.exception.LicensePlateUniqueViolationException;
import com.pitang.securecarpark.securecarpark.exception.LoginUniqueViolationException;
import com.pitang.securecarpark.securecarpark.repository.CarRepository;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional(readOnly = true)
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Car not found"));
    }

    @Transactional
    public Car saveCar(Car car) {

        try {
            return carRepository.save(car);
        } catch (DataIntegrityViolationException ex) {
            throw new LicensePlateUniqueViolationException("License plate already exists");
        }
    }

    @Transactional
    public Car updateCar(Car car, Car updatedCar) {

        car.setYear(updatedCar.getYear());
        car.setColor(updatedCar.getColor());
        car.setModel(updatedCar.getModel());
        car.setLicensePlate(updatedCar.getLicensePlate());

        return carRepository.save(car);
    }
    @Transactional
    public ResponseEntity<?> deleteCarById(Long id) {
        carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        carRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
