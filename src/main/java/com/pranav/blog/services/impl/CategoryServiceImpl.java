package com.pranav.blog.services.impl;

import com.pranav.blog.entities.Category;
import com.pranav.blog.exceptions.ResourceNotFoundException;
import com.pranav.blog.payloads.CategoryDto;
import com.pranav.blog.repositories.CategoryRepo;
import com.pranav.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = convertToEntity(categoryDto);
        // TODO: check if category already exists
        // save in repo with exception handling
        try {
            categoryRepo.save(category);
        }
        catch (Exception e){
          System.out.println(e);
        }
        return categoryDto;
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("category","CategoryId",id));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("category","CategoryId",id));
        categoryDto.setId(id);
        categoryRepo.save(convertToEntity(categoryDto));
        return categoryDto;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("category","CategoryId",id));
        return convertToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CategoryDto convertToDto(Category category){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setCategoryName(category.getCategoryName());
            categoryDto.setCategoryDescription(category.getCategoryDescription());
            return categoryDto;
    }
    public  Category convertToEntity(CategoryDto categoryDto){
            Category category = new Category();
            category.setId(categoryDto.getId());
            category.setCategoryName(categoryDto.getCategoryName());
            category.setCategoryDescription(categoryDto.getCategoryDescription());
            return category;
    }
}
