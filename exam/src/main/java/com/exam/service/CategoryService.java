package com.exam.service;

import com.exam.model.exam.Category;
import com.exam.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category){
        return categoryRepository.save(category);
    }

    public Set<Category> getCategories(){
        return new LinkedHashSet<>(categoryRepository.findAll());

    }

    public Category findCategoryById(long id){
        return categoryRepository.findById(id).get();
    }

    public void deleteCategory(long id){
         categoryRepository.deleteById(id);
    }

}
