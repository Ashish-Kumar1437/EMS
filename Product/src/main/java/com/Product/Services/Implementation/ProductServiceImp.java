package com.Product.Services.Implementation;

import com.Product.Models.Entities.ProductEntity;
import com.Product.Models.Entities.VariantEntity;
import com.Product.Repositories.ProductRepository;
import com.Product.Repositories.VariantRepository;
import com.Product.Services.Interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    ProductRepository productRepository;
    VariantRepository variantRepository;

    @Override
    public boolean addProducts(List<ProductEntity> products) {

        for(ProductEntity product:products){
            List<VariantEntity> variants = product.getVariants();
            ProductEntity savedProduct = productRepository.save(product);
            for(VariantEntity variant : variants){
                variant.setProduct(savedProduct);
            }
            variantRepository.saveAll(variants);
        }

        return true;
    }
}
