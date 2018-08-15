package com.domain.services;

import com.domain.repository.*;
import com.domain.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Munzir Masri on 11.8.2018.
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Long id){
        return categoryRepository.findOne(id);
    }

    public List<Category> findByOwnerUserId(Long userOwnerId){
        return categoryRepository.findByOwnerUserId(userOwnerId);
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

}
