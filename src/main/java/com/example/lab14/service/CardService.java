package com.example.lab14.service;

import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import com.example.lab14.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public void addCard(Card card) {
        cardRepository.save(card);
    }

    public void deleteCard(int id) {
        cardRepository.deleteById(id);
    }

    public Card getCard(int id) {
        return cardRepository.getCardById(id);
    }

    public Iterable<Card> getCards() {
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

