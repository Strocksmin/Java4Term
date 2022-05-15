package com.example.lab14.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String homePage() {
        return "<html>\n" +
                "<head><title>Home</title></head>\n" +
                "<body>\n" +
                "Фамилия: Карабанов<br><hr>\nИмя: Андрей<br><hr>\nНомер группы: ИКБО-30-20<br><hr>\nНомер варианта: 13<hr>" +
                "</body>\n" +
                "</html>";
    }

}
