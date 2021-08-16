package com.met.transaction.repository;

import com.met.transaction.document.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findAllBySenderAccountNumberOrRecipientAccountNumber(Long sender, Long recipient);
    List<Transaction> findAllBySenderUserIdOrRecipientUserId(String sender, String recipient);
}
