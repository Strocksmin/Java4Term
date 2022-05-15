package com.example.lab14.controller;

import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import com.example.lab14.service.CardService;
import com.example.lab14.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/card")
    public String add(@RequestParam String number, @RequestParam int code,
                      Model model)
    {
        emailService.sendAddCardEmail(new Card(number, code));
        cardService.addCard(new Card(number, code));
        return "card";
    }

    @GetMapping("card/del/{id}")
    public String del(@PathVariable int id, Model model) {
        cardService.deleteCard(id);
        return "card";
    }

    @GetMapping("/card")
    public String card(Model model) {
        model.addAttribute("cards", cardService.getCards());
        return "card";
    }

    @RequestMapping("/cards")
    public @ResponseBody Iterable<Card> banks(Model model) {
        return cardService.getCards();
    }

    @GetMapping("/card/{cardId}/bank")
    public @ResponseBody Bank getCardBank(@PathVariable int cardId) {
        return cardService.getBankbyCard(cardId);
    }

    @GetMapping("/getCardByCode")
    public @ResponseBody List<Card> filterCode(Model model) {
        return cardService.getByCode();
    }

    @GetMapping("/getCardByNumber")
    public @ResponseBody List<Card> filterNumber(Model model) {
        return cardService.getByNumber();
    }
}