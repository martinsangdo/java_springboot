package com.firstdata.main_pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firstdata.main_pack.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByName(String name);
}
