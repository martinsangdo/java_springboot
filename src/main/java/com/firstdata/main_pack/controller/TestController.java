package com.firstdata.main_pack.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.firstdata.main_pack.model.TestItem;

/*
This is the RESTful controller
 */
@RestController
@RequestMapping("/api/test")
public class TestController {
    @RequestMapping(value = "/filterItems", method = RequestMethod.POST)
    public List filterItems(@RequestBody TestItem param) {
        String filterCondition = param.getFilterCondition();
        List<String> results = new ArrayList<>();
        List<String> items = Arrays.asList("apple", "banana", "apricot", "blueberry", "orange");
        int len = items.size();
        for (int i=0; i<len; i++){
            if (items.get(i).contains(filterCondition)){
                results.add(items.get(i));
            }
        }

        return results;
    }
}
