package com.car_rental_application.CarRentalApplication.Repository;

import com.car_rental_application.CarRentalApplication.Entity.BookCar;
import com.car_rental_application.CarRentalApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);

    User findUserById(Long userId);

    @Query("SELECT customer FROM User customer WHERE customer.type='USER'")
    List<User> listAllCustomers();
}
