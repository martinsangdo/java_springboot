package com.firstdata.main_pack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        List<String> productNames = new ArrayList<>();
        productNames.add("Samsung");

        return new ResponseEntity<>(productNames, HttpStatus.OK);
    }

    @GetMapping("/product/detail")
    public ResponseEntity getProductById(
        @RequestParam String id
    ){
        System.out.println("Id value: " + id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/product/detail")
    public ResponseEntity createNewProduct(
        @RequestParam String name,
        @RequestParam(required = false) String price,
        @RequestParam(defaultValue = "yellow") String color
    ){
        System.out.println("Name value: " + name);
        System.out.println("Price value: " + price);
        System.out.println("Color value: " + color);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/product/detail/body")
    public ResponseEntity createNewProductFromBodyParam(
        @RequestBody Object productDetail
    ){
        System.out.println("Body data: " + productDetail.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/category/detail")
    public ResponseEntity updateCategory(
        @RequestParam String name,
        @RequestParam(required = false) String price,
        @RequestParam(defaultValue = "red") String color
    ){
        System.out.println("Name value: " + name);
        System.out.println("Price value: " + price);
        System.out.println("Color value: " + color);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/category/detail/body")
    public ResponseEntity updateCategoryFromBody(
        @RequestBody Object productDetail
    ){
        System.out.println("Body data: " + productDetail.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
