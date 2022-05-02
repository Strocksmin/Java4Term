package com.example.lab14.service;


import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class CardService {
    private final SessionFactory sessionFactory;
    private Session session;

    public CardService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    public void addCard(Card card) {
        session.beginTransaction();
        session.saveOrUpdate(card);
        session.getTransaction().commit();
    }

    public void deleteCard(int id) {
        session.beginTransaction();
        Card c = session.load(Card.class, id);
        session.delete(c);
        session.getTransaction().commit();
    }

    public Card getCard(int id) {
        return session.createQuery("select c from Card c where c.id ='" + id + "'",
                Card.class).getSingleResult();
    }

    public List<Card> getCards() {
        return session.createQuery("select c from Card c", Card.class)
                .getResultList();
    }


    public Bank getBankbyCard(int cardId) {
        return session.createQuery("select c from Card c where c.id =:id", Card.class)
                .setParameter("id", cardId).getSingleResult().getBank();
    }

}

