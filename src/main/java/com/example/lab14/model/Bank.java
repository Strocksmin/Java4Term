package com.example.lab14.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "banks")
@Getter
@Setter
public class Bank {
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @Id
    @SequenceGenerator(name = "banks_seq", sequenceName =
            "banks_sequence", allocationSize = 1)
    @GeneratedValue(generator = "banks_seq", strategy =
            GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;


    @OneToMany(mappedBy = "bank")
    private List<Card> cards;

    public Bank() {

    }

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
        return "Банк " + name + ", адрес = " + address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
