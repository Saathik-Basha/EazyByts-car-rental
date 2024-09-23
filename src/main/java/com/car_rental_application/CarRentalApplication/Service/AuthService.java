package com.car_rental_application.CarRentalApplication.Service;

import com.car_rental_application.CarRentalApplication.Entity.User;
import com.car_rental_application.CarRentalApplication.Repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private AuthRepository repository;
    public User saveUser(User user) {
        return repository.save(user);
    }

    public User findUserByEmail(String email) {
        return repository.findUserByEmail(email);
    }

    public User findUserById(Long userId) {
        return repository.findUserById(userId);
    }

    public List<User>  listAllCustomers(){
        return repository.listAllCustomers();
    }
}
