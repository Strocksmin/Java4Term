package com.example.lab14.controller;

import com.example.lab14.data.UserData;
import com.example.lab14.exception.UserAlreadyExistsException;
import com.example.lab14.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("userData", new UserData());
        return "account/register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid  UserData userData, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", userData);
            return "account/register";
        }
        try {
            userService.register(userData);
        } catch (UserAlreadyExistsException e){
            bindingResult.rejectValue("email", "userData.email","Аккаунт с такой почтой уже существует.");
            model.addAttribute("registrationForm", userData);
            return "account/register";
        }
        return "redirect:/home";
    }
}
