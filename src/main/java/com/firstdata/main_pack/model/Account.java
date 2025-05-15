package com.firstdata.main_pack.model;

import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
@Table(name = "tb_account")
public class Account {
    // @Id
    // Long id;
    String name;
    String email;
}
