package com.met.transaction.service.impl;

import com.met.transaction.api.AccountServiceApi;
import com.met.transaction.document.Transaction;
import com.met.transaction.dto.request.ExecuteTransactionRequest;
import com.met.transaction.dto.response.ExecuteTransactionResponse;
import com.met.transaction.exception.ErrorCode;
import com.met.transaction.exception.TransactionServiceException;
import com.met.transaction.repository.TransactionRepository;
import com.met.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountServiceApi accountServiceApi;

    @Override
    public Transaction createTransaction(Transaction transaction, String token) {
        ExecuteTransactionRequest executeTransactionRequest = new ExecuteTransactionRequest();
        executeTransactionRequest.setAccountFrom(transaction.getSender().getAccountNumber());
        executeTransactionRequest.setAccountTo(transaction.getRecipient().getAccountNumber());
        executeTransactionRequest.setAmount(transaction.getAmount());

        ExecuteTransactionResponse executeTransactionResponse = accountServiceApi.execute(token, executeTransactionRequest);

        if (executeTransactionResponse.isSuccess()) {
            transaction.getSender().setUserId(executeTransactionResponse.getFromId());
            transaction.getRecipient().setUserId(executeTransactionResponse.getToId());
            transaction.setTransactionTime(LocalDateTime.now());
            return transactionRepository.save(transaction);
        } else {
            throw new TransactionServiceException(ErrorCode.FAILED_TRANSACTION, executeTransactionResponse.getErrorMessage());
        }
    }

    @Override
    public Transaction getById(String id) {
        return transactionRepository.findById(id).orElseThrow(
                () -> new TransactionServiceException(ErrorCode.NOT_FOUND, "Transaction with that id not found")
        );
    }

    @Override
    public List<Transaction> getForAccount(Long account) {
        return transactionRepository.findAllBySenderAccountNumberOrRecipientAccountNumber(account, account);
    }

    @Override
    public List<Transaction> getForUser(String userId) {
        return transactionRepository.findAllBySenderUserIdOrRecipientUserId(userId, userId);
    }
}
