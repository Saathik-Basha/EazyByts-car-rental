package com.car_rental_application.CarRentalApplication.Repository;

import com.car_rental_application.CarRentalApplication.Entity.BookCar;
import com.car_rental_application.CarRentalApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCarRepository extends JpaRepository<BookCar,Long> {
    BookCar findBookingById(Long id);

    @Query("SELECT car FROM BookCar car WHERE car.user.id = :id AND car.status=2 OR car.status=3 OR car.status=4")
    List<BookCar> listAllMyBooking(@Param("id") Long Id);

    @Query("SELECT booking FROM BookCar booking WHERE booking.owner_id = :owner_id")
    List<BookCar> listAllBookingByOwnerId(@Param("owner_id") Long owner_id);

    @Query("SELECT booking FROM BookCar booking WHERE booking.id = :id")
    List<BookCar> getBookingByOwnerId(@Param("id") Long id);

    List<BookCar> findAllByOrderByIdDesc();
}
