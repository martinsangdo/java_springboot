package com.firstdata.main_pack.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;


@Service
public class OrderService {
    public List<String> getHistoricalOrders(String userId){
        List<String> orders = Arrays.asList("pencil", "book", "ruler");
        return orders;
    }
}
