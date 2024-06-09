package com.Product.Repositories;

import com.Product.Models.Entities.CategoryEnitity;
import com.Product.Models.Entities.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity,Long> {

    List<SubCategoryEntity> findAllByCategory(CategoryEnitity category);

}
