package com.car_rental_application.CarRentalApplication.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private SearchCar searchCar;
    private double total_amount;
    private int status;
    private Long no_of_days;
    private Long owner_id;
    @OneToOne
    private Car car;
}
