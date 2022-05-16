package com.example.lab14.service;

import com.example.lab14.model.Bank;
import com.example.lab14.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public void addBank(Bank bank) {
        log.info("Add bank {}", bank);
        bankRepository.save(bank);
    }

    public void deleteBank(int id) {
        log.info("Delete bank with id {}", id);
        bankRepository.deleteById(id);
    }

    public Bank getBank(int id) {
        log.info("Get bank by id {}", id);
        return bankRepository.getBankById(id);
    }

    public List<Bank> getBanks() {
        log.info("Get all banks");
        return bankRepository.findAll();
    }

    public List<Bank> getByName() {
        return bankRepository.findByOrderByName();
    }

    public List<Bank> getByAddress() {
        return bankRepository.findByOrderByAddress();
    }
}
