package com.Product.Controller;

import com.Product.Models.Entities.CategoryEnitity;
import com.Product.Models.Entities.SubCategoryEntity;
import com.Product.Services.Interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
@AllArgsConstructor
public class CategoryController {

    CategoryService categoryService;

    @GetMapping("")
    public List<CategoryEnitity> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public List<SubCategoryEntity> getSubCateory(@PathVariable(value = "categoryId") Long categoryId) throws Exception {
        return categoryService.getSubCateory(categoryId);
    }

    @PostMapping("/add")
    public boolean addCategory(@RequestBody List<CategoryEnitity> categories) {
        return categoryService.addCategory(categories);
    }

    @DeleteMapping("/{id}")
    public boolean removeCategory(@PathVariable(value = "id") Long id) throws Exception {
        return categoryService.removeCategory(id);
    }

    @PostMapping("/sub/add")
    public boolean addSubCategory(@RequestBody List<SubCategoryEntity> subCategories,@RequestParam Long categoryId) throws Exception {
        return categoryService.addSubCategory(subCategories,categoryId);
    }

    @DeleteMapping("/sub/{id}")
    public boolean removeSubCategory(@PathVariable(value = "id") Long id) {
        return categoryService.removeSubCategory(id);
    }

    @PostMapping("/update")
    public boolean updateCategory(@RequestBody CategoryEnitity category) throws Exception {
        return categoryService.updateCategory(category);
    }

    @PostMapping("/sub/update")
    public boolean updateSubCategory(@RequestBody SubCategoryEntity subCategory) throws Exception {
        return categoryService.updateSubCategory(subCategory);
    }

}
