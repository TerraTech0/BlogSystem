package com.example.blogsystem.Service;
import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id, Category category){
        Category c = categoryRepository.findCategoriesByCategoryId(id);
        if (c == null){
            throw new ApiException("category id not found!");
        }
        c.setName(category.getName());
        categoryRepository.save(c);
    }

    public void deleteCategory(Integer id){
        Category category = categoryRepository.findCategoriesByCategoryId(id);
        if (category == null){
            throw new ApiException("category id not found!");
        }
        categoryRepository.delete(category);
    }
}
