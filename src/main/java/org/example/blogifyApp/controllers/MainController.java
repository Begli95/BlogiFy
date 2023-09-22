package org.example.blogifyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title","Фотостудия");
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title","Страница про нас");
        return "about";
    }
}
