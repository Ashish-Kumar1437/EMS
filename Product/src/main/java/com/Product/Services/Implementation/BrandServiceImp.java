package com.Product.Services.Implementation;

import com.Product.Models.Entities.BrandEntity;
import com.Product.Repositories.BrandRepository;
import com.Product.Services.Interfaces.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImp implements BrandService {

    BrandRepository brandRepository;


    @Override
    public List<BrandEntity> brandList() {
        return brandRepository.findAll();
    }

    @Override
    public boolean addBrand(List<BrandEntity> brand) {
        brandRepository.saveAll(brand);
        return true;
    }

    @Override
    public boolean updateBrand(BrandEntity brand) throws Exception {
        BrandEntity oldBrand = getBrand(brand.getId());

        brandRepository.save(brand);

        return true;
    }

    @Override
    public boolean removeBrand(Long brandId) throws Exception {
        BrandEntity brand = getBrand(brandId);
        brandRepository.delete(brand);
        return true;
    }

    private BrandEntity getBrand(Long id) throws Exception {
        return brandRepository.findById(id).orElseThrow(() -> new Exception("Brand not present."));
    }
}
