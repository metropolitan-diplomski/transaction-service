package com.met.transaction.document;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Transaction {
    @Id
    private String id;
    @NotNull
    private Person sender;
    @NotNull
    private Person recipient;
    @NotEmpty
    private String transactionDescription;
    @NotNull
    private Integer transactionCode;
    private String identifier;
    private Double amount;
}
