package com.car_rental_application.CarRentalApplication.ValueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarValueObject {
    private Long id;
    private String Brand;
    private String Modal;
    private String car_number;
    private int no_of_seats;
    private String type;
    private Double rate;
    private String location;
    private Owner owner;
    private boolean active_state;
}
