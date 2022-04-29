package com.example.lab14.controller;

import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Card")
@Controller
public class CardController {
    List<Card> list = new ArrayList<>();

    @GetMapping("/add")
    public String add(@RequestParam(name = "number", required = false, defaultValue = "default") String number,
                      @RequestParam(name = "code", required = false , defaultValue = "default") int code,
                      Model model)
    {
        list.add(new Card(number, code));
        model.addAttribute("number", number);
        model.addAttribute("code", code);
        return "Card";
    }
    @GetMapping("/{number}")
    public String get(@PathVariable String number, Model model) {
        model.addAttribute("number", "not found");
        model.addAttribute("code", "not found");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCardNumber().equals(number)) {
                model.addAttribute("number", list.get(i).getCardNumber());
                model.addAttribute("code", list.get(i).getCode() );
            }
        }
        return "Card";
    }
    @GetMapping("/del/{number}")
    public String del(@PathVariable String number, Model model) {
        model.addAttribute("number", "not found");
        model.addAttribute("code", "not found");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCardNumber().equals(number)) {
                model.addAttribute("number", list.get(i).getCardNumber());
                model.addAttribute("code", list.get(i).getCode() );
                list.remove(i);
                break;
            }
        }
        return "Card";
    }
    @GetMapping("/all")
    public String all(Model model) {
        String[] numbers = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            numbers[i] = list.get(i).getCardNumber();
        }
        model.addAttribute("numbers", numbers);
        return "AllCards";
    }
}
