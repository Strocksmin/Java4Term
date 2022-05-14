package com.example.lab14.controller;

import com.example.lab14.model.Bank;
import com.example.lab14.model.Card;
import com.example.lab14.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/bank")
    public String add(@RequestParam String name, @RequestParam String address,
                      Model model)
    {
        bankService.addBank(new Bank(name, address));
        return "bank";
    }

    @GetMapping("bank/del/{id}")
    public String del(@PathVariable int id, Model model) {
        bankService.deleteBank(id);
        return "bank";
    }

    @GetMapping("/bank")
    public String bank(Model model) {
        model.addAttribute("banks", bankService.getBanks());
        return "bank";
    }

    @RequestMapping("/banks")
    public @ResponseBody List<Bank> banks(Model model) {
        return bankService.getBanks();
    }

    @GetMapping("/getBankByName")
    public @ResponseBody List<Bank> filterCode(Model model) {
        return bankService.getByName();
    }

    @GetMapping("/getBankByAddress")
    public @ResponseBody List<Bank> filterNumber(Model model) {
        return bankService.getByAddress();
    }
}
