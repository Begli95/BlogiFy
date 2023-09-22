package org.example.blogifyApp.controllers;

import org.example.blogifyApp.models.User;
import org.example.blogifyApp.models.enums.Role;
import org.example.blogifyApp.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping ("/registration")
    public String userAdd(Model model,
                          @RequestParam String name,
                          @RequestParam String password,
                          CsrfToken csrfToken){
        System.out.println("Saving new user with name: " + name);
        String tokenValue = csrfToken.getToken();
        if(userRepository.findByName(name) != null) {
            model.addAttribute("errorMessage","Пользователь именем "+name+" существует!");
            return "registration";
        }
        User user = new User(name);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(Role.ROLE_USER);
        System.out.println("Saving new user with name: " + name);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }
}
