package com.Product.Services.Implementation;

import com.Product.Enums.AttributeEnum;
import com.Product.Models.Entities.AttributeEntity;
import com.Product.Repositories.AttributeRepository;
import com.Product.Services.Interfaces.AttributeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttributeServiceImp implements AttributeService {

    AttributeRepository attributeRepository;

    @Override
    public boolean addAttribute(List<AttributeEntity> attributes) {

        attributeRepository.saveAll(attributes);

        return true;
    }

    @Override
    public boolean removeAttribute(Long id) throws Exception {
        attributeRepository.delete(attributeRepository.findById(id).orElseThrow(() -> new Exception("Attribute Not Present.")));
        return true;
    }

    @Override
    public List<AttributeEntity> getAttributes(AttributeEnum type) {
        return attributeRepository.findAllByName(type);
    }
}
