package org.example.photoApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/") //при переходе на главную страницу будет вызываться функция ниже index
    public String index(Model model){ //передается один обязательный параметр model, который всегда принимается
        //с помощью этой модели мы передем внутрь шаблона мы передаем параметр по названию title со значением "Фотостудия"
        model.addAttribute("title","Фотостудия");
        return "index"; //тут мы вызывам шаблон index.html
    }
}
