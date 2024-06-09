package com.Product.Repositories;

import com.Product.Models.Entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity,Long> {
}
