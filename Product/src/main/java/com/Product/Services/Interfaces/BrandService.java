package com.Product.Services.Interfaces;

import com.Product.Models.Entities.BrandEntity;

import java.util.List;

public interface BrandService {

    List<BrandEntity> brandList();

    boolean addBrand(List<BrandEntity> brand);

    boolean updateBrand(BrandEntity brand) throws Exception;

    boolean removeBrand(Long brandId) throws Exception;
}
