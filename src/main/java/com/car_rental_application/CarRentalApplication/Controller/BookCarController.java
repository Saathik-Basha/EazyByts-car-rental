package com.car_rental_application.CarRentalApplication.Controller;

import com.car_rental_application.CarRentalApplication.Entity.BookCar;
import com.car_rental_application.CarRentalApplication.Entity.Car;
import com.car_rental_application.CarRentalApplication.Entity.SearchCar;
import com.car_rental_application.CarRentalApplication.Entity.User;
import com.car_rental_application.CarRentalApplication.Service.AuthService;
import com.car_rental_application.CarRentalApplication.Service.BookCarService;
import com.car_rental_application.CarRentalApplication.Service.CarService;
import com.car_rental_application.CarRentalApplication.Service.SearchCarService;
import com.car_rental_application.CarRentalApplication.ValueObject.CarValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class BookCarController {
    @Autowired
    private SearchCarService searchCarService;
    @Autowired
    private AuthService authService;
    @Autowired
    private BookCarService bookCarService;
    @Autowired
    private CarService carService;
    @Autowired
    private HomeController homeController;

    private final int PENDGING = 1;
    private final int BOOKED = 2;
    private final int ONRIDE = 3;
    private final int RETURN = 4;

    @PostMapping("/book-car/search-id/{id}")
    public String bookMyCar(@PathVariable Long id, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Long userId = Long.parseLong((String) session.getAttribute("userId"));
        User user = authService.findUserById(userId);
        SearchCar searchCar = searchCarService.findSearchById(id);
        BookCar bookCar = new BookCar();

        double total_amount;
        Long no_of_days = ChronoUnit.DAYS.between(searchCar.getStart_date(), searchCar.getEnd_date());
        if(no_of_days == 0){
            total_amount = 1 * Double.parseDouble(request.getParameter("carRate"));
            bookCar.setNo_of_days(1L);
        } else {
            total_amount = Double.parseDouble(request.getParameter("carRate")) * no_of_days;
            bookCar.setNo_of_days(no_of_days);
        }


        Car car = carService.findCarById(Long.parseLong(request.getParameter("carId")));
        bookCar.setCar(car);
        bookCar.setOwner_id(Long.parseLong(request.getParameter("ownerId")));
        bookCar.setUser(user);
        bookCar.setSearchCar(searchCar);
        bookCar.setTotal_amount(total_amount);
        bookCar.setStatus(PENDGING);
        car.setActive_state(true);
        bookCarService.saveBookingDetails(bookCar);
        carService.updateState(car);
        model.addAttribute("carDetails",car);
        model.addAttribute("bookingDetails",bookCarService.saveBookingDetails(bookCar));
        model.addAttribute("userName",homeController.getSessionUserName(request));
        System.out.println(car);
        return "PaymentPage";
    }

    @PostMapping("/book-car/payment/{id}")
    public String updateStatus(@PathVariable Long id, Model model, HttpServletRequest request){
       BookCar bookCar = bookCarService.findBookingById(id);
       bookCar.setStatus(BOOKED);
       bookCarService.saveBookingDetails(bookCar);
       model.addAttribute("userName",homeController.getSessionUserName(request));
       return "ConfirmationPage";
    }

    @RequestMapping("/my-booking")
    public String listAllMyBooking(HttpServletRequest request,Model model){
        List myBooking = bookCarService.listAllMyBooking(homeController.getSessionUserId(request));
        model.addAttribute("userName",homeController.getSessionUserName(request));
        model.addAttribute("myBookingList",myBooking);
        return "MyBookingPage";
    }

}
