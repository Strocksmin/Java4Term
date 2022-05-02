package com.example.lab14.service;

import com.example.lab14.model.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class BankService {
    private final SessionFactory sessionFactory;
    private Session session;

    public BankService(SessionFactory sessionFactory) {
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
}
