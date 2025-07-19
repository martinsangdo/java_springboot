package com.firstdata.main_pack.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    OrderService orderService;

    public List<String> getOrders(String userId){
        return orderService.getHistoricalOrders(userId);
    }
    
    
    public Boolean validEmailFormat(String emailAddress){
        String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailPattern)
            .matcher(emailAddress)
            .matches();
    }
}
