package com.pranav.blog.services;
import com.pranav.blog.payloads.CategoryDto;
import java.util.List;

public interface CategoryService {
    public CategoryDto addCategory(CategoryDto categoryDto);
    public void deleteCategory(Integer id);
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
    public CategoryDto getCategoryById(Integer id);
    public List<CategoryDto> getAllCategories();
}
