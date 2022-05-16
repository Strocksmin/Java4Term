package com.example.lab14.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {
    @Column(name = "number")
    private String cardNumber;
    @Column(name = "code")
    private int code;

    @Id
    @SequenceGenerator(name = "banks_seq", sequenceName =
            "banks_sequence", allocationSize = 1)
    @GeneratedValue(generator = "banks_seq", strategy =
            GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;


    public Bank getBank() {
        return bank;
    }

    @ManyToOne
    public Bank bank;

    public Card() {

    }

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
