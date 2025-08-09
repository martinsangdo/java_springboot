package com.firstdata.main_pack.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class EmployeeService {

    public List<HashMap<String, Object>> searchEmployees(List data, String keyword){
        //
        return new ArrayList<>();
    }
    
}
