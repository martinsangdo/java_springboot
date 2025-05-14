package com.firstdata.main_pack.controller;

import org.springframework.web.bind.annotation.*;

/*
This is the RESTful controller to get/set data to database
 */
@RestController
@RequestMapping("/api/house")
public class HouseController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name") String name) {
        return "Hello "+ name;
    }
}
