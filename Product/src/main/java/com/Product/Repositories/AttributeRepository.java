package com.Product.Repositories;

import com.Product.Enums.AttributeEnum;
import com.Product.Models.Entities.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<AttributeEntity,Long> {

    List<AttributeEntity> findAllByName(AttributeEnum name);
}
