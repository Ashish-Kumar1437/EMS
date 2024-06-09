package com.Product.Services.Interfaces;

import com.Product.Models.DTOs.ProductDTO;
import com.Product.Models.Entities.ProductEntity;

import java.util.List;

public interface ProductService {

    boolean addProducts(List<ProductEntity> products);

}
