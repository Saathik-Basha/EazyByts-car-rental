package com.car_rental_application.CarRentalApplication.Controller;

import com.car_rental_application.CarRentalApplication.Entity.User;
import com.car_rental_application.CarRentalApplication.Service.AuthService;
import com.car_rental_application.CarRentalApplication.Service.JoinAsPartnerService;
import com.car_rental_application.CarRentalApplication.ValueObject.Owner;
import com.car_rental_application.CarRentalApplication.ValueObject.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JoinAsPartnerController {
    @Autowired
    private JoinAsPartnerService partnerService;
    @Autowired
    private HomeController homeController;
    @Autowired
    private AuthService authService;


    @RequestMapping("/join-as-partner")
    public String joinAsPartner(){
        return "PartnerWithUsPage";
    }

    @PostMapping("/join-as-partner/")
    public String savePartner(HttpServletRequest req, Model model){
        Owner owner = new Owner();
        owner.setContact_name(req.getParameter("contactName"));
        owner.setBusiness_name(req.getParameter("businessName"));
        owner.setAddress(req.getParameter("address"));
        owner.setPassword(req.getParameter("password"));
        owner.setLocation(req.getParameter("location"));
        owner.setPhone_number(req.getParameter("contactNumber"));
        owner.setEmail(req.getParameter("email"));
        partnerService.savePartner(owner);

        User user = new User(req.getParameter("contactName"),req.getParameter("email"),req.getParameter("password"),"OWNER");
        authService.saveUser(user);
        return "PartnerConfirmationPage";
    }
}
