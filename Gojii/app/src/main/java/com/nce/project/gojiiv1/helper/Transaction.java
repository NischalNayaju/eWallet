package com.nce.project.gojiiv1.helper;

import java.sql.Timestamp;

public class Transaction {
    public String id ;
    public int accountNumber ;
    public String operation ;
    public int amount ;
    public String reference;
    public Timestamp createdAt ;


    public Transaction(String id, int accountNumber, String operation, int amount, String reference, Timestamp createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
        this.reference = reference;
        this.createdAt = createdAt;
    }
}
