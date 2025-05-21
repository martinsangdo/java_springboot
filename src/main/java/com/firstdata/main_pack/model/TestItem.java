package com.firstdata.main_pack.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class TestItem {
    String filterCondition;
}
