package com.pranav.blog.repositories;

import com.pranav.blog.entities.Category;
import com.pranav.blog.entities.Post;
import com.pranav.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String keyword);
}
