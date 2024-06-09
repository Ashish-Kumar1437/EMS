package com.Product.Repositories;

import com.Product.Models.Entities.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<VariantEntity,Long> {
}
