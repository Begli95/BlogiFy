package org.example.photoApp.controllers;

import lombok.RequiredArgsConstructor;
import org.example.photoApp.models.User;
import org.example.photoApp.services.UserService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String userAdd(User user, Model model, CsrfToken csrfToken) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "The user with the name " + user.getName() + " exists!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }
}
