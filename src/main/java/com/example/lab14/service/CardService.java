package com.example.lab14.service;

import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import com.example.lab14.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public void addCard(Card card) {
        log.info("Add card {}", card);
        cardRepository.save(card);
    }

    public void deleteCard(int id) {
        log.info("Delete card with id {}", id);
        cardRepository.deleteById(id);
    }

    public Card getCard(int id) {
        log.info("Get card by id {}", id);
        return cardRepository.getCardById(id);
    }

    public Iterable<Card> getCards() {
        log.info("Get all cards");
        return cardRepository.findAll();
    }


    public Bank getBankbyCard(int cardId) {
        return cardRepository.getCardById(cardId).getBank();
    }

    public List<Card> getByCode() {
        return cardRepository.findByOrderByCode();
    }

    public List<Card> getByNumber() {
        return cardRepository.findByOrderByCardNumber();
    }
}

