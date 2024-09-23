package com.car_rental_application.CarRentalApplication.Service;

import com.car_rental_application.CarRentalApplication.ValueObject.Owner;
import com.car_rental_application.CarRentalApplication.ValueObject.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JoinAsPartnerService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseTemplateVO savePartner(Owner owner) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Owner partner = restTemplate.postForObject(
                "http://localhost:8081/join-as-partner/register",
                owner,
                Owner.class);
        vo.setOwner(partner);
        return vo;
    }
}
