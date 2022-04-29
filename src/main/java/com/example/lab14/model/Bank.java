package com.example.lab14.model;

public class Bank {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Bank(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Банк " + name + ", адрес ='" + address;
    }

}
