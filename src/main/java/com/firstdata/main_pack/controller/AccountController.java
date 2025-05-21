package com.firstdata.main_pack.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firstdata.main_pack.model.Account;
import com.firstdata.main_pack.service.AccountService;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/api/account/name")
    public ResponseEntity<Account> getAccountByName(@RequestParam String keyword){
        Account ac = accountService.findAnyAccountByName(keyword);
        if (Objects.nonNull(ac)){
            return new ResponseEntity<>(ac, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/account/name")
    public ResponseEntity<Account> createNewAccount(@RequestBody Account body){
        Account savedAc = accountService.createNewAccount(body.getName(), body.getEmail());
        return new ResponseEntity<>(savedAc, HttpStatus.OK);
    }
    /*
     * 
     * import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest registrationRequest, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors
            StringBuilder errorMessage = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errorMessage.append(error.getField())
                                .append(": ")
                                .append(error.getDefaultMessage())
                                .append("; "));
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        // Process the valid registration request
        // ...

        return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
    }
}public class UserRegistrationRequest {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Min(value = 18, message = "You must be at least 18 years old")
    private int age;

    // Getters and setters...
}
     */
}
