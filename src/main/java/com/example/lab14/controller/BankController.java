package com.example.lab14.controller;

import com.example.lab14.model.Bank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Bank")
@Controller
public class BankController {
    List<Bank> list = new ArrayList<>();

    @GetMapping("/add")
    public String add(@RequestParam(name = "name", required = false, defaultValue = "default") String name,
                      @RequestParam(name = "address", required = false , defaultValue = "default") String address,
                      Model model)
    {
        list.add(new Bank(name, address));
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        return "Bank";
    }
    @GetMapping("/{name}")
    public String get(@PathVariable String name, Model model) {
        model.addAttribute("name", "not found");
        model.addAttribute("address", "not found");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                model.addAttribute("name", list.get(i).getName());
                model.addAttribute("address", list.get(i).getAddress() );
            }
        }
        return "Bank";
    }
    @GetMapping("/del/{name}")
    public String del(@PathVariable String name, Model model) {
        model.addAttribute("name", "not found");
        model.addAttribute("address", "not found");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                model.addAttribute("name", list.get(i).getName());
                model.addAttribute("address", list.get(i).getAddress() );
                list.remove(i);
                break;
            }
        }
        return "Bank";
    }
    @GetMapping("/all")
    public String all(Model model) {
        String[] names = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            names[i] = list.get(i).getName();
        }
        model.addAttribute("names", names);
        return "AllBanks";
    }
}
