package org.example.photoApp.controllers;

import lombok.RequiredArgsConstructor;
import org.example.photoApp.models.Post;
import org.example.photoApp.models.User;
import org.example.photoApp.services.BlogService;
import org.example.photoApp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private final UserService userService;

    @GetMapping("/blog")
    public ModelAndView blogMain() {
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<Post> posts = blogService.getAllPosts();
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName("blog-main");
        return modelAndView;
    }

    @GetMapping("/blog/add")
    public ModelAndView blogAdd(Model model, Authentication authentication) {
        ModelAndView blogAddModel = new ModelAndView();
        blogAddModel.setViewName("blog-add");
        model.addAttribute("userMessage", "User  " + authentication.getName());
        return blogAddModel;
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(Post post, Model model, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        blogService.addBlogPost(post, user.getName());
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Post> post = blogService.getPostById(id);
        if (!post.isPresent()) {
            return "redirect:/blog";
        }
        model.addAttribute("post", post.get());
        return "blog-detail";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model, Principal principal) {
        Optional<Post> post = blogService.getPostById(id);
        if (!post.isPresent()) {
            return "redirect:/blog";
        }
        model.addAttribute("post", post.get());
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam("title") String title,
                                 @RequestParam("anons") String anons,
                                 @RequestParam("full_text") String full_text, Principal principal) {
        Optional<Post> existingPost = blogService.getPostById(id);
        if (!existingPost.isPresent()) {
            return "redirect:/blog";
        }
        blogService.editBlogPost(title, anons, full_text, id);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(Post post, @PathVariable(value = "id") long id) {
        blogService.removeBlogPost(id);
        return "redirect:/blog";
    }
}

