package com.nce.project.gojiiv1.helper;

public class Consumer {
    private String sc, name;
    private Integer consumerID, amount;

    public Consumer(String sc, String name, Integer consumerID, Integer amount) {
        this.sc = sc;
        this.name = name;
        this.consumerID = consumerID;
        this.amount = amount;
    }

    public String getSc() {
        return sc;
    }

    public String getName() {
        return name;
    }

    public Integer getConsumerID() {
        return consumerID;
    }

    public Integer getAmount() {
        return amount;
    }
}

