package com.example.lab14.repository;

import com.example.lab14.model.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends CrudRepository<Bank, Integer> {
    Bank getBankById(int id);
    List<Bank> findAll();

    List<Bank> findByOrderByAddress();
    List<Bank> findByOrderByName();
}
