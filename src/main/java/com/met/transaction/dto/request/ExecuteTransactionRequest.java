package com.met.transaction.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ExecuteTransactionRequest {
    @NotNull
    private Long accountFrom;
    @NotNull
    private Long accountTo;
    @NotNull
    private Double amount;
}
