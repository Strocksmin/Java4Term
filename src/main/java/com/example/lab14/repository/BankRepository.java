package com.example.lab14.repository;

import com.example.lab14.model.Bank;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankRepository extends CrudRepository<Bank, Integer> {
    Bank getBankById(int id);

    List<Bank> findByOrderByAddress();
    List<Bank> findByOrderByName();
}