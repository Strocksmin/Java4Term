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
public class BankService {
    private final SessionFactory sessionFactory;
    private Session session;

    private CriteriaBuilder builder;
    private CriteriaQuery<Bank> criteriaQuery;
    private Root<Bank> root;

    public BankService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
        builder = session.getCriteriaBuilder();
        criteriaQuery = builder.createQuery(Bank.class);
        root = criteriaQuery.from(Bank.class);
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    public void addBank(Bank bank) {
        session.beginTransaction();
        session.saveOrUpdate(bank);
        session.getTransaction().commit();
    }

    public void deleteBank(int id) {
        session.beginTransaction();
        Bank b = session.load(Bank.class, id);
        session.delete(b);
        session.getTransaction().commit();
    }

    public Bank getBank(int id) {
        return session.createQuery("select b from Bank b where b.id ='" + id + "'",
                Bank.class).getSingleResult();
    }

    public List<Bank> getBanks() {
        return session.createQuery("select b from Bank b", Bank.class)
                .getResultList();
    }

    public List<Bank> getByName() {
        criteriaQuery.select(root).orderBy(builder.asc(root.get("name")));
        Query<Bank> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Bank> getByAddress() {
        criteriaQuery.select(root).orderBy(builder.asc(root.get("address")));
        Query<Bank> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
