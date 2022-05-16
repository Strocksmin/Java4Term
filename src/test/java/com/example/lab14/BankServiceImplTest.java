package com.example.lab14;

import com.example.lab14.model.Bank;
import com.example.lab14.repository.BankRepository;
import com.example.lab14.service.BankService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BankServiceImplTest {
    @Mock
    private BankRepository bankRepository;
    @Captor
    ArgumentCaptor<Bank> captor;
    
    @Test
    public void getBanks(){
        Bank bank = new Bank();
        bank.setName("MCIB");
        Bank bank2 = new Bank();
        bank2.setName("Tinkoff");
        Mockito.when(bankRepository.findAll()).thenReturn(List.of(bank, bank2));
        BankService bankService = new BankService(bankRepository);
        Assertions.assertEquals(2, bankService.getBanks().size());
        Assertions.assertEquals("MCIB", bankService.getBanks().get(0).getName());
    }

    @Test
    void addNewBank(){
        Bank bank = new Bank();
        bank.setName("Erolia");
        BankService bankService = new BankService(bankRepository);
        bankService.addBank(bank);
        Mockito.verify(bankRepository).save(captor.capture());
        Bank captured = captor.getValue();
        Assertions.assertEquals("Erolia", captured.getName());
    }
}
