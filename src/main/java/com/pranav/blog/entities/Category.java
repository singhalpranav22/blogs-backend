package com.pranav.blog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// org.springframework.dao.DataIntegrityViolationException:
@Entity
@Table(name="category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String categoryName;
    private String categoryDescription;
}
