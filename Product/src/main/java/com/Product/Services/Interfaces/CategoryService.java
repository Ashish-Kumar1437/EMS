package com.Product.Services.Interfaces;

import com.Product.Models.Entities.CategoryEnitity;
import com.Product.Models.Entities.SubCategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEnitity> getCategories();

    List<SubCategoryEntity> getSubCateory(Long categoryId) throws Exception;

    boolean addCategory(List<CategoryEnitity> categories);

    boolean removeCategory(Long id) throws Exception;

    boolean addSubCategory(List<SubCategoryEntity> subCategories,Long categoryId) throws Exception;

    boolean removeSubCategory(Long id);

    boolean updateCategory(CategoryEnitity category) throws Exception;

    boolean updateSubCategory(SubCategoryEntity subCategory) throws Exception;
}
