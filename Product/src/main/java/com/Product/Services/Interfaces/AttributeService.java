package com.Product.Services.Interfaces;

import com.Product.Enums.AttributeEnum;
import com.Product.Models.Entities.AttributeEntity;

import java.util.List;

public interface AttributeService {

    boolean addAttribute(List<AttributeEntity> attributes);

    boolean removeAttribute(Long id) throws Exception;

    List<AttributeEntity> getAttributes(AttributeEnum type);
}
