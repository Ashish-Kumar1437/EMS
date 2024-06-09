package com.Product.Models.DTOs;

import com.Product.Models.Entities.ProductEntity;
import com.Product.Models.Entities.VariantEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO extends ProductEntity {

    List<VariantEntity> variants;
}
