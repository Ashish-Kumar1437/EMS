package com.Product.Controller;

import com.Product.Models.Entities.BrandEntity;
import com.Product.Services.Interfaces.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@CrossOrigin("*")
@AllArgsConstructor
public class BrandController {

    BrandService brandService;

    @GetMapping("")
    List<BrandEntity> brandList() {
        return brandService.brandList();
    }

    @PostMapping("/add")
    boolean addBrand(@RequestBody List<BrandEntity> brand) {
        return brandService.addBrand(brand);
    }

    @PutMapping("/update")
    boolean updateBrand(@RequestBody BrandEntity brand) throws Exception {
        return brandService.updateBrand(brand);
    }

    @DeleteMapping("/remove/{brandId}")
    boolean removeBrand(@PathVariable(value = "brandId")  Long brandId) throws Exception {
        return brandService.removeBrand(brandId);
    }
}
