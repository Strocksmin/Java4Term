package com.example.lab14.service;

import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import com.example.lab14.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public void addBank(Bank bank) {
        bankRepository.save(bank);
    }

    public void deleteBank(int id) {
        bankRepository.deleteById(id);
    }

    public Bank getBank(int id) {
        return bankRepository.getBankById(id);
    }

    public Iterable<Bank> getBanks() {
        return bankRepository.findAll();
    }

    public List<Bank> getByName() {
        return bankRepository.findByOrderByName();
    }

    public List<Bank> getByAddress() {
        return bankRepository.findByOrderByAddress();
    }
}
