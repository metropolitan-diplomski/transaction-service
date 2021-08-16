package com.met.transaction.service;

import com.met.transaction.document.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction, String token);
    Transaction getById(String id);
    List<Transaction> getForAccount(Long account);
    List<Transaction> getForUser(String userId);
}
