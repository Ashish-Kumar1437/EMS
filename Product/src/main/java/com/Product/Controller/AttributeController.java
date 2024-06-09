package com.Product.Controller;

import com.Product.Enums.AttributeEnum;
import com.Product.Models.Entities.AttributeEntity;
import com.Product.Services.Interfaces.AttributeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attribute")
@CrossOrigin("*")
@AllArgsConstructor
public class AttributeController {

    AttributeService attributeService;

    @PostMapping("/add")
    boolean addAttribute(@RequestBody List<AttributeEntity> attributes){
        return attributeService.addAttribute(attributes);
    };

    @DeleteMapping("/delete")
    boolean removeAttribute(@RequestParam Long id) throws Exception{
        return attributeService.removeAttribute(id);
    };

    @GetMapping("/{type}")
    List<AttributeEntity> getAttributes(@PathVariable(value = "type")AttributeEnum type){
        return attributeService.getAttributes(type);
    }
}
