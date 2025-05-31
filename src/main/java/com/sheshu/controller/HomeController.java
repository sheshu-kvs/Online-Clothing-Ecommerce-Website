package com.sheshu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "index";  // This should map to templates/home.html
    }
}