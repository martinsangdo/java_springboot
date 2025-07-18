package com.firstdata.main_pack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        List<String> productNames = new ArrayList<>();
        productNames.add("Samsung");

        return new ResponseEntity<>(productNames, HttpStatus.OK);
    }
}
