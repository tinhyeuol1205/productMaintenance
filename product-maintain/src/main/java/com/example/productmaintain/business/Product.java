package com.example.productmaintain.business;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Product implements Serializable {
    private String code;
    private String description;
    private double price;
}
