package com.car_rental_application.CarRentalApplication.Controller;

import com.car_rental_application.CarRentalApplication.Entity.SearchCar;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.car_rental_application.CarRentalApplication.Entity.User;
import com.car_rental_application.CarRentalApplication.Service.AuthService;
import com.car_rental_application.CarRentalApplication.Service.CarService;
import com.car_rental_application.CarRentalApplication.Service.SearchCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    Boolean isAuthenticated;
    @Autowired
    private AuthService authService;
    @Autowired
    private SearchCarService searchCarService;
    @Autowired
    private CarService carService;
    HttpSession session;

    public String index(Model model,HttpServletRequest request){
        session = request.getSession();
        if(isAuthenticated == true){
            System.out.println("exist");
            isAuthenticated = true;
            model.addAttribute("isAuthenticated",isAuthenticated);
        } else{
            System.out.println("non exist");
            isAuthenticated = false;
            model.addAttribute("isAuthenticated",isAuthenticated);
        }
        model.addAttribute("userName",getSessionUserName(request));
        return "HomePage";
    }

    @RequestMapping("/")
    public String isAuthenticated(boolean value,Model model,HttpServletRequest request){
        isAuthenticated = value;
        return index(model,request);
    }

    @PostMapping("/search")
    public String searchCar(HttpServletRequest request,Model model) throws ParseException {
        SearchCar car = new SearchCar();
        car.setLocation(request.getParameter("location"));
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        car.setStart_date(startDate);
        car.setEnd_date(endDate);
        Long userId = getSessionUserId(request);
        User user = authService.findUserById(userId);
        car.setUser(user);
        searchCarService.saveSearch(car);
        List carList = getAllCarsByLocation(request.getParameter("location"));
        model.addAttribute("carList",carList);
        model.addAttribute("searchCarId",searchCarService.saveSearch(car).getId());
        model.addAttribute("searchDetails",searchCarService.saveSearch(car));
        model.addAttribute("userName",getSessionUserName(request));
        return "SearchResultPage";
    }

    public List getAllCarsByLocation(String location){
        return carService.getAllCarsByLocation(location);
    }

    public String getSessionUserName(HttpServletRequest request){
        session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        return userName;
    }

    public Long getSessionUserId(HttpServletRequest request){
        session = request.getSession();
        Long userId = Long.parseLong((String) session.getAttribute("userId"));
        return userId;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,Model model){
        isAuthenticated = false;
        model.addAttribute("isAuthenticated",isAuthenticated);
        return "HomePage";

    }

    @RequestMapping("/index")
    public String redirectIndex(Model model,HttpServletRequest request){
        isAuthenticated = true;
        return index(model,request);
    }
}
