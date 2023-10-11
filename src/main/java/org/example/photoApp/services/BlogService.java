package org.example.photoApp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.photoApp.models.Post;
import org.example.photoApp.models.User;
import org.example.photoApp.repo.PostRepository;
import org.example.photoApp.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ArrayList<Post> getAllPosts() {
        return (ArrayList<Post>) postRepository.findAll();
    }

    public void addBlogPost(Post post, String name) {
        User user = userRepository.findByName(name);
        post.setUser(user);
        postRepository.save(post);
    }

    public void editBlogPost(String title, String anons, String fulltext, long id) {
        //findById выбрасывает exception
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setTitle(title);
            existingPost.setAnons(anons);
            existingPost.setFull_text(fulltext);
            postRepository.save(existingPost);
        }
    }

    public void removeBlogPost(long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            User user = post.getUser();
            if (user != null) {
                user.getPosts().remove(post);
            }
            postRepository.delete(post);
        }
    }

    public Optional<Post> getPostById(long id) {
        if (!postRepository.existsById(id)) {
            return Optional.empty();
        }
        return postRepository.findById(id);
    }
}
