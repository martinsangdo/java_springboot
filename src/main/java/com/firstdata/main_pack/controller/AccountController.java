package com.firstdata.main_pack.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firstdata.main_pack.model.Account;
import com.firstdata.main_pack.service.AccountService;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/api/account/name")
    public String getAccountByName(@RequestParam String keyword){
        Account ac = accountService.findAnyAccountByName(keyword);
        if (Objects.nonNull(ac)){
            return ac.getName();
        }
        return "";
    }
}
