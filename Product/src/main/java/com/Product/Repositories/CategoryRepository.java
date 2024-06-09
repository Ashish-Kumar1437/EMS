package com.Product.Repositories;

import com.Product.Models.Entities.CategoryEnitity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEnitity,Long> {
}
