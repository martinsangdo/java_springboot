package com.firstdata.main_pack.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

// import lombok.Data;

@Entity
@Data
@Table(name = "tblDepartments")
public class Account {
    @Id
    Long id;
    String name;
    String email;
}
