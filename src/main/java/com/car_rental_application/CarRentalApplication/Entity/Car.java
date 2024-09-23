package com.car_rental_application.CarRentalApplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    private Long id;
    private String Brand;
    private String Modal;
    private String car_number;
    private int no_of_seats;
    private String type;
    private Double rate;
    private String location;
    private boolean active_state;
}
