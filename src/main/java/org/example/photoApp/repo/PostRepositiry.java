package org.example.photoApp.repo;

import org.example.photoApp.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepositiry extends CrudRepository<Post,Long> {

}
