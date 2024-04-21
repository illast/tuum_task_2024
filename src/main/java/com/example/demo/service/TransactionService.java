package com.example.demo.service;

import com.example.demo.dto.TransactionCreateDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.dto.TransactionRequest;
import com.example.demo.exception.ApplicationException;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.model.Account;
import com.example.demo.model.Balance;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.util.TransactionDirection;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.demo.util.Validators.*;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private  final BalanceRepository balanceRepository;
    private final TransactionMapper transactionMapper;

    public List<TransactionDTO> getTransactions(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ApplicationException("Account " + accountId + " not found"));
        List<Transaction> transactions = transactionRepository.findAllByAccount(account);
        return transactionMapper.toDtoList(transactions);
    }

    @Transactional
    public TransactionCreateDTO saveTransaction(TransactionRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ApplicationException("Account " + request.getAccountId() + " not found"));
        double balance = balanceRepository.findByAccountAndCurrency(account, request.getCurrency()).getAvailableAmount();
        validateTransactionRequest(request, balance);
        balance = updateAccountBalance(request, balance);
        Transaction transaction = createTransaction(request, balance);
        account.addTransaction(transaction);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toCreateDto(savedTransaction);
    }

    private void validateTransactionRequest(TransactionRequest request, double balance) {
        validateCurrency(request.getCurrency());
        validateDirection(request.getDirection());
        validateAmount(request.getAmount());
        validateDescription(request.getDescription());
        validateSufficientFunds(balance, request.getAmount(), request.getDirection());
    }

    private Transaction createTransaction(TransactionRequest request, double balance) {
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setDirection(request.getDirection());
        transaction.setDescription(request.getDescription());
        transaction.setBalanceAfterTransaction(balance);
        return transaction;
    }

    private double updateAccountBalance(TransactionRequest request, double balance) {
        if (Objects.equals(request.getDirection(), TransactionDirection.IN.name())) {
            balanceRepository.addToBalance(request.getAccountId(), request.getCurrency(), request.getAmount());
            balance += request.getAmount();
        } else {
            balanceRepository.removeFromBalance(request.getAccountId(), request.getCurrency(), request.getAmount());
            balance -= request.getAmount();
        }
        return balance;
    }
}
