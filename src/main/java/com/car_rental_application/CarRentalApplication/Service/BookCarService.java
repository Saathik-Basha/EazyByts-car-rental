package com.car_rental_application.CarRentalApplication.Service;

import com.car_rental_application.CarRentalApplication.Entity.BookCar;
import com.car_rental_application.CarRentalApplication.Entity.User;
import com.car_rental_application.CarRentalApplication.Repository.BookCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCarService {
    @Autowired
    private BookCarRepository repository;

    public BookCar saveBookingDetails(BookCar bookCar) {

        return repository.save(bookCar);
    }

    public BookCar findBookingById(Long id) {
        return repository.findBookingById(id);
    }

    public List<BookCar> listAllMyBooking(long userId) {
        return repository.listAllMyBooking(userId);
    }

    public List<BookCar> listAllBookingByOwnerId(Long owner_id){
        return repository.listAllBookingByOwnerId(owner_id);
    }

    public List<BookCar> getBookingByOwnerId(Long id) {
        return repository.getBookingByOwnerId(id);
    }

    public List<BookCar> listAllBooking(){
        return repository.findAllByOrderByIdDesc();
    }

}
