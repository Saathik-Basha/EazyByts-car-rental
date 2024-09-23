package com.car_rental_application.CarRentalApplication.ValueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    private Long id;
    private String contact_name;
    private String business_name;
    private String email;
    private String password;
    private String phone_number;
    private String address;
    private String location;
}
