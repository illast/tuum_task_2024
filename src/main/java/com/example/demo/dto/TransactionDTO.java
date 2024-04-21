package com.example.demo.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long accountId;
    private Long transactionId;
    private double amount;
    private String currency;
    private String direction;
    private String description;
}
