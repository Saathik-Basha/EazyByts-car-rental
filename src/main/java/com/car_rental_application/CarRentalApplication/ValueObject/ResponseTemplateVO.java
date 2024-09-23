package com.car_rental_application.CarRentalApplication.ValueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Owner owner;
    private CarValueObject carValueObject;
}
