package com.example.lab14.model;

public class Card {
    private String cardNumber;
    private int code;

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCode() {
        return code;
    }

    public Card(String cardNumber, int code) {
        this.cardNumber = cardNumber;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Банковская карта:" + "номер: " + cardNumber + ", код - " + code;
    }
}
