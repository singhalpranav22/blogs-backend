package com.pranav.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private int id;
    @NotNull(message = "Category name cannot be empty.")
    @Size(min = 3,max = 90,message = "Category name should have >=3 and <=90 characters.")
    private String categoryName;
    @NotNull(message = "Category description cannot be empty.")
    @Size(max = 100,message = "Category description can be at most 100 characters long.")
    private String categoryDescription;
}
