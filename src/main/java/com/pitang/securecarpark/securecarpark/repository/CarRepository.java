package com.pitang.securecarpark.securecarpark.repository;

import com.pitang.securecarpark.securecarpark.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByLicensePlate(String licensePlate);
}
