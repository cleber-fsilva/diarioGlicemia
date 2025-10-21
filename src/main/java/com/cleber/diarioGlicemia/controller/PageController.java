package com.cleber.diarioGlicemia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/dashboard.html")
    public String dashboard() {
        return "dashboard";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
