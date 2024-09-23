package com.car_rental_application.CarRentalApplication.Service;

import com.car_rental_application.CarRentalApplication.Entity.Car;
import com.car_rental_application.CarRentalApplication.Repository.CarRepository;
import com.car_rental_application.CarRentalApplication.ValueObject.CarValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CarRepository repository;

    public List getAllCarsByLocation(String location) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("http://localhost:8081/get-all-cars/{location}", HttpMethod.GET,
                entity, List.class, location).getBody();

    }

    public CarValueObject getCarById(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("http://localhost:8081/get-car/{id}", HttpMethod.GET,
                entity, CarValueObject.class, id).getBody();
    }

    public Car findCarById(Long id) {
        return repository.findCarById(id);
    }

    public Car updateState(Car car) {
        return  repository.save(car);
    }
}
