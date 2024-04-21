package com.example.demo.util;

import com.example.demo.exception.ApplicationException;

import java.util.Objects;

public class Validators {

    public static void validateCurrency(String currency) {
        try {
            Currency.valueOf(currency);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Invalid currency: " + currency);
        }
    }

    public static void validateDirection(String direction) {
        try {
            TransactionDirection.valueOf(direction);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Invalid direction: " + direction);
        }
    }

    public static void validateAmount(double amount) {
        if (amount < 0) {
            throw new ApplicationException("Invalid amount: " + amount);
        }
    }

    public static void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new ApplicationException("Description is missing");
        }
    }

    public static void validateSufficientFunds(double accountBalance, double transactionAmount, String direction) {
        if (Objects.equals(direction, TransactionDirection.OUT.name()) && accountBalance < transactionAmount) {
            throw new ApplicationException("Insufficient funds");
        }
    }
}