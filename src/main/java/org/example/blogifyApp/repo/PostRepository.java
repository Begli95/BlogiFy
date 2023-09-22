package org.example.blogifyApp.repo;

import org.example.blogifyApp.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {

}
