package org.example.photoApp.controllers;

import org.example.photoApp.models.Post;
import org.example.photoApp.repo.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BlogController {
    private final PostRepository postRepository;

    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/blog")
    public ModelAndView blogMain() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Post> posts = postRepository.findAll();
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
            System.out.println();
        }
        return modelAndView;
    }
    /*@GetMapping("/blog/add")
    public ModelAndView blogAdd() {
        ModelAndView blogAddModel = new ModelAndView();
        blogAddModel.setViewName("blog-add");
        return blogAddModel;
    }*/
    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

}

