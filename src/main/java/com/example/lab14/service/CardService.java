package com.example.lab14.service;

import com.example.lab14.model.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

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

    public List<Card> getCard(int id) {
        return session.createQuery("select c from Card c where c.id ='" + id + "'",
                Card.class).getResultList();
    }

    public List<Card> getCards() {
        return session.createQuery("select c from Card c", Card.class)
                .getResultList();
    }
}

