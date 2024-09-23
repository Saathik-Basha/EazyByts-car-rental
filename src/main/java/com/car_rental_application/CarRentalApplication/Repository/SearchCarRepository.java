package com.car_rental_application.CarRentalApplication.Repository;

import com.car_rental_application.CarRentalApplication.Entity.SearchCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchCarRepository extends JpaRepository<SearchCar,Long> {
    SearchCar findSearchById(Long id);
}
