package com.example.demo.controller;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.AccountRequest;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long accountId) {
        AccountDTO accountDTO = accountService.getAccount(accountId);
        return ResponseEntity.ok(accountDTO);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountRequest request) {
        AccountDTO accountDTO = accountService.saveAccount(request);
        return ResponseEntity.ok(accountDTO);
    }
}
