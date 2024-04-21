package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class AccountRequest {
    private Long customerId;
    private String country;
    private List<String> currencies;
}