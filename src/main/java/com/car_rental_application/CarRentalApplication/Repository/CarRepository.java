package com.car_rental_application.CarRentalApplication.Repository;

import com.car_rental_application.CarRentalApplication.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    Car findCarById(Long id);

}
