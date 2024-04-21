package com.example.demo.controller;

import com.example.demo.dto.TransactionCreateDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.dto.TransactionRequest;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable Long accountId) {
        List<TransactionDTO> transactionDTOS = transactionService.getTransactions(accountId);
        return ResponseEntity.ok(transactionDTOS);
    }

    @PostMapping()
    public ResponseEntity<TransactionCreateDTO> saveTransaction(@RequestBody TransactionRequest request) {
        TransactionCreateDTO transactionCreateDTO = transactionService.saveTransaction(request);
        return ResponseEntity.ok(transactionCreateDTO);
    }
}
