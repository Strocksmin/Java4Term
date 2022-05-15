package com.example.lab14.service;

import com.example.lab14.SchedulerBean;
import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import com.example.lab14.repository.BankRepository;
import com.example.lab14.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ScheduleService implements SchedulerBean {
    @Autowired
    private final BankRepository bankRepository;

    @Autowired
    private final CardRepository cardRepository;

    public ScheduleService(BankRepository bankRepository, CardRepository cardRepository) {
        this.bankRepository = bankRepository;
        this.cardRepository = cardRepository;
    }

    private Boolean isEmpty(final File file) {
        return (file.isDirectory() && (file.list().length > 0));
    }

    @ManagedOperation
    @Scheduled(cron = "0 * * * * *")
    public void doScheduledTask() throws IOException {
            for (File myFile : new File("C:\\Users\\MrDru\\z6").listFiles()) {
                if (myFile.isFile()) myFile.delete();
            }

        List<Bank> banks = bankRepository.findAll();
        List<Card> cards = cardRepository.findAll();

        for (int i = 0; i < banks.size(); i++) {
            File bank = new File("C:\\Users\\MrDru\\z6\\bank_" + i + ".txt");
            FileWriter writer = new FileWriter(bank, true);
            writer.write(banks.get(i).toString());
            writer.close();
        }

        for (int i = 0; i < cards.size(); i++) {
            File card = new File("C:\\Users\\MrDru\\z6\\card_" + i + ".txt");
            FileWriter writer = new FileWriter(card, true);
            writer.write(cards.get(i).toString());
            writer.close();
        }
    }
}
