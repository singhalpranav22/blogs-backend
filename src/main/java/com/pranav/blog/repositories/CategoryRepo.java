package com.pranav.blog.repositories;
import com.pranav.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
