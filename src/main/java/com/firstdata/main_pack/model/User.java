package com.firstdata.main_pack.model;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor

@Builder
@AllArgsConstructor
public class User {
    @NonNull
    private String id;

    private String address;
    private String position;


    // @Getter //
    // @Setter //
    private String username;

    // @Getter //
    private String name;
    // public void setName(String _name) {
    //     this.name = _name;
    // }

    // @Setter //
    private String email;
    // public String getEmail() {
    //     return email;
    // }
}