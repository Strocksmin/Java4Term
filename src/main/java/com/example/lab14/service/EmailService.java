package com.example.lab14.service;

import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    @Async
    public void sendAddBankEmail(Bank bank){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mireaspringexample@bk.ru");
        message.setTo("aekarabanov@yandex.ru");
        message.setSubject("Message from Spring Boot Application");
        message.setText(bank.toString());

        this.emailSender.send(message);
        System.out.println("Email successfully sent!");
    }

    @Async
    public void sendAddCardEmail(Card card){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mireaspringexample@bk.ru");
        message.setTo("aekarabanov@yandex.ru");
        message.setSubject("Message from Spring Boot Application");
        message.setText(card.toString());

        this.emailSender.send(message);
        System.out.println("Email successfully sent!");
    }
}
