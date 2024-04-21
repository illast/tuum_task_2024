package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {
    private Long accountId;
    private Long customerId;
    private List<BalanceDTO> balances;
}
