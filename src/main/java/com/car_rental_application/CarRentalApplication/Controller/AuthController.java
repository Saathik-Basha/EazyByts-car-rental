package com.car_rental_application.CarRentalApplication.Controller;

import com.car_rental_application.CarRentalApplication.Entity.User;
import com.car_rental_application.CarRentalApplication.Repository.AuthRepository;
import com.car_rental_application.CarRentalApplication.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private HomeController homeController;

    @PostMapping("/register")
    public String registerUser(HttpServletRequest request,Model model){
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setAddress(request.getParameter("address"));
        user.setLocation(request.getParameter("place"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setPhone_number(request.getParameter("phoneNumber"));
        user.setType("USER");
        authService.saveUser(user);
        return homeController.index(model,request);
    }

    @PostMapping("/login")
    public String login(HttpServletRequest req, Model model){
        User user = authService.findUserByEmail(req.getParameter("userEmail"));
        if(!(Objects.isNull(user))){
            if(user.getPassword().equals(req.getParameter("userPassword")) && user.getType().equals("USER")){
                HttpSession session = req.getSession();
                session.setAttribute("userName",user.getName());
                session.setAttribute("userId",String.valueOf(user.getId()));
                System.out.println("loged in");
                return homeController.isAuthenticated(true,model,req);
            }
            else {
                model.addAttribute("message","Invalid Credentials");
                return homeController.isAuthenticated(false,model,req);
            }
        }
        else {
            model.addAttribute("message","Invalid Credentials");
            return homeController.isAuthenticated(false,model,req);
        }
    }

}
