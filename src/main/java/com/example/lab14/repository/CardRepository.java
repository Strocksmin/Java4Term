package com.example.lab14.repository;

import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
    Card getCardById(int id);
    List<Card> findAll();
    List<Card> findByOrderByCardNumber();
    List<Card> findByOrderByCode();
}
