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
}
