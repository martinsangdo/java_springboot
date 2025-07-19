package com.firstdata.main_pack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firstdata.main_pack.service.AccountService;

@RestController
public class DemoController {
    @Autowired
    AccountService accountService;

    @GetMapping("/api/account/orders")
    public ResponseEntity<List<String>> getOrders(
        @RequestParam String userId
    ){
        List<String> orders = accountService.getOrders(userId);
        System.out.println(orders);
        return new ResponseEntity<List<String>>(orders, HttpStatus.OK);
    }

    @PostMapping("/api/account/signUp")
    public ResponseEntity<Boolean> signUpAccount(
        @RequestParam String emailAddress
    ){
        Boolean isValidEmail = accountService.validEmailFormat(emailAddress);
        return new ResponseEntity<Boolean>(isValidEmail, HttpStatus.OK);
    }

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

    @DeleteMapping("/order/detail")
    public ResponseEntity deleteOrder(
        @RequestParam String id
    ){
        System.out.println("Id value: " + id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/orders/detail/body")
    public ResponseEntity deleteOrdersFromBody(
        @RequestBody Object ids
    ){
        System.out.println("Body data: " + ids.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PostMapping("/api/account/signUp")
    // public ResponseEntity signUpAccount(
    //     @RequestParam String emailAddress
    // ){
    //     Boolean isValidEmail = accountService.validEmailFormat(emailAddress);
    //     return new ResponseEntity<>(isValidEmail, HttpStatus.OK);
    // }
}
