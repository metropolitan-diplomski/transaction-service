package com.met.transaction.document;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Person {
    @NotEmpty
    private String accountNumber;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String address;
    private String userId; // koristi se ako je transkacija ka/od korisnika banke
}
