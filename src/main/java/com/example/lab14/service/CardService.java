package com.example.lab14.service;


import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class CardService {
    private final SessionFactory sessionFactory;
    private Session session;

    private CriteriaBuilder builder;
    private CriteriaQuery<Card> criteriaQuery;
    private Root<Card> root;

    public CardService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
        builder = session.getCriteriaBuilder();
        criteriaQuery = builder.createQuery(Card.class);
        root = criteriaQuery.from(Card.class);
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

    public List<Card> getByCode() {
        criteriaQuery.select(root).orderBy(builder.asc(root.get("code")));
        Query<Card> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Card> getByNumber() {
        criteriaQuery.select(root).orderBy(builder.asc(root.get("code")));
        Query<Card> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}

