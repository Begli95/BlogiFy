package org.example.blogifyApp.repo;

import org.example.blogifyApp.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByName(String name);
}
