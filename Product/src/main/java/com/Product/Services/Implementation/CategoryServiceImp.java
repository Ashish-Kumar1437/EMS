package com.Product.Services.Implementation;

import com.Product.Models.Entities.CategoryEnitity;
import com.Product.Models.Entities.SubCategoryEntity;
import com.Product.Repositories.CategoryRepository;
import com.Product.Repositories.SubCategoryRepository;
import com.Product.Services.Interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {

    SubCategoryRepository subCategoryRepository;
    CategoryRepository categoryRepository;


    @Override
    public List<CategoryEnitity> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<SubCategoryEntity> getSubCateory(Long categoryId) throws Exception {
        CategoryEnitity category = getCategory(categoryId);

        return subCategoryRepository.findAllByCategory(category);
    }

    @Override
    public boolean addCategory(List<CategoryEnitity> categories) {
        for (CategoryEnitity category : categories) {
            if (category.getId() != null) {
                if (categoryRepository.findById(category.getId()).isPresent()) {
                    continue;
                }
            }
            categoryRepository.save(category);
        }
        return true;
    }

    @Override
    public boolean removeCategory(Long id) throws Exception {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean addSubCategory(List<SubCategoryEntity> subCategories, Long categoryId) throws Exception {
        CategoryEnitity category = getCategory(categoryId);

        for (SubCategoryEntity subCate : subCategories) {
            if (subCate.getId() != null) {
                if (subCategoryRepository.findById(subCate.getId()).isPresent()) {
                    continue;
                }
            }
            subCate.setCategory(category);
            subCategoryRepository.save(subCate);
        }
        return true;
    }

    @Override
    public boolean removeSubCategory(Long id) {
        subCategoryRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateCategory(CategoryEnitity category) throws Exception {
        CategoryEnitity oldCategory = getCategory(category.getId());

        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean updateSubCategory(SubCategoryEntity subCategory) throws Exception {
        SubCategoryEntity oldSubCategory = subCategoryRepository.findById(subCategory.getId()).orElseThrow(()->new Exception("Sub category not preset."));

        subCategoryRepository.save(subCategory);
        return true;
    }



    private CategoryEnitity getCategory(Long categoryId) throws Exception {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new Exception("Category Not Preset"));
    }
}
