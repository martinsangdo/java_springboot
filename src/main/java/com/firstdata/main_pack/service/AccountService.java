package com.firstdata.main_pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstdata.main_pack.model.Account;
import com.firstdata.main_pack.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account findAnyAccountByName(String name){
        Account db = accountRepository.findByName(name);
        return db;
    }

    public Account createNewAccount(String name, String email){
        Account db = new Account();
        db.setEmail(email);
        db.setName(name);
        accountRepository.save(db);
        return db;
    }
}
