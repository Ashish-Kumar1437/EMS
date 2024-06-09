package com.Product.Models.Entities;

import com.Product.Enums.AttributeEnum;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "attributes")
@Data
public class AttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    @Enumerated(EnumType.STRING)
    AttributeEnum name;

    @Column(nullable = false,unique = true)
    String values;
}
