package com.car_rental_application.CarRentalApplication.Service;

import com.car_rental_application.CarRentalApplication.Entity.SearchCar;
import com.car_rental_application.CarRentalApplication.Repository.SearchCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchCarService {
    @Autowired
    private SearchCarRepository repository;

    public SearchCar saveSearch(SearchCar car) {
        return repository.save(car);
    }

    public SearchCar findSearchById(Long id) {
        return repository.findSearchById(id);
    }
}
