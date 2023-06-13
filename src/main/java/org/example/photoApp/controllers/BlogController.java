package org.example.photoApp.controllers;

import org.example.photoApp.models.Post;
import org.example.photoApp.repo.PostRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @Autowired
    private PostRepositiry postRepositiry;
    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepositiry.findAll();
        model.addAttribute("posts",posts);
        return  "blog-main";
    }
}
