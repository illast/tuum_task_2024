package com.example.demo.service;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.AccountRequest;
import com.example.demo.exception.ApplicationException;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.model.Account;
import com.example.demo.model.Balance;
import com.example.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.example.demo.util.Validators.validateCurrency;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDTO getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ApplicationException("Account " + accountId + " not found"));
        return accountMapper.toDto(account);
    }

    public AccountDTO saveAccount(AccountRequest request) {
        Account account = new Account();
        account.setCustomerId(request.getCustomerId());
        account.setCountry(request.getCountry());

        for (String currency : request.getCurrencies()) {
            validateCurrency(currency);
            Balance balance = createBalance(currency);
            account.addBalance(balance);
        }

        Account savedAccount = accountRepository.save(account);
        return accountMapper.toDto(savedAccount);
    }

    private Balance createBalance(String currency) {
        Balance balance = new Balance();
        balance.setCurrency(currency);
        balance.setAvailableAmount(0);
        return balance;
    }
}
