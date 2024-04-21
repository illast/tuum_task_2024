package com.example.demo.repository;

import com.example.demo.model.Account;
import com.example.demo.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Modifying
    @Query("UPDATE balance b SET b.availableAmount = b.availableAmount + :amount WHERE b.account.accountId = :accountId AND b.currency = :currency")
    void addToBalance(Long accountId, String currency, double amount);

    @Modifying
    @Query("UPDATE balance b SET b.availableAmount = b.availableAmount - :amount WHERE b.account.accountId = :accountId AND b.currency = :currency")
    void removeFromBalance(Long accountId, String currency, double amount);

    Balance findByAccountAndCurrency(Account account, String currency);
}
