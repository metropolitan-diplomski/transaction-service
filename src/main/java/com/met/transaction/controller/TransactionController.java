package com.met.transaction.controller;

import com.met.transaction.document.Transaction;
import com.met.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @PostMapping("/")
    ResponseEntity<Transaction> createTransaction(HttpServletRequest request, @Valid @RequestBody Transaction transaction) {
        String authToken = request.getHeader(this.tokenHeader);
        return ResponseEntity.ok(transactionService.createTransaction(transaction, authToken));
    }

    @GetMapping("/{id}")
    ResponseEntity<Transaction> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(transactionService.getById(id));
    }

    @GetMapping("/")
    ResponseEntity<?> search(
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "accountNumber", required = false) Long accountNumber) {
        if (userId != null) {
            return ResponseEntity.ok(transactionService.getForUser(userId));
        } else if (accountNumber != null) {
            return ResponseEntity.ok(transactionService.getForAccount(accountNumber));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
