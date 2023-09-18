package org.example.photoApp.controllers;

import org.example.photoApp.models.Post;
import org.example.photoApp.models.User;
import org.example.photoApp.repo.PostRepository;
import org.example.photoApp.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class BlogController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public BlogController(PostRepository postRepository,UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/blog")
    public ModelAndView blogMain() {
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<Post> posts = (ArrayList<Post>) postRepository.findAll();
        //Iterable<Post> posts = postRepository.findAll();
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName("blog-main");
        //для теста
        System.out.println("Записи из таблицы post:");
        for (Post post : posts) {
            System.out.println("Идентификатор: " + post.getId());
            System.out.println("Заголовок: " + post.getTitle());
            System.out.println("Анонс: " + post.getAnons());
            System.out.println("Полный текст статьи: " + post.getFull_text());
            System.out.println("Просмотры: " + post.getViews());
            //System.out.println("Автор" + post.);
            System.out.println();
        }
        return modelAndView;
    }
    @GetMapping("/blog/add")
    public ModelAndView blogAdd(Model model,Authentication authentication) {
        ModelAndView blogAddModel = new ModelAndView();
        blogAddModel.setViewName("blog-add");
        model.addAttribute("userMessage","Пользователь  "+authentication.getName());
        return blogAddModel;
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              Model model,Principal principal){
        Post post = new Post(title, anons, full_text);
        User user = getUserByPrincipal(principal);
        post.setUser(user);
        postRepository.save(post);
        return "redirect:/blog";
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null) return new User();
        return userRepository.findByName(principal.getName());
    }

    private Optional<Post> getPostById(long id) {
        if (!postRepository.existsById(id)) {
            return Optional.empty();
        }
        return postRepository.findById(id);
    }
    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable(value = "id") long id, Model model){
        Optional<Post> post = getPostById(id);
        if (!post.isPresent()) { return "redirect:/blog"; }
        model.addAttribute("post", post.get());
        return "blog-detail";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        Optional<Post> post = getPostById(id);
        if (!post.isPresent()) { return "redirect:/blog"; }
        model.addAttribute("post", post.get());
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(
                              @PathVariable(value = "id") long id,
                              @RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              Model model){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(
            @PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }
}

