package com.Product.Models.Entities;

import com.Product.Enums.CategoryTypeEnum;
import com.Product.Enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "category")
@Data
public class CategoryEnitity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    CategoryTypeEnum type;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<SubCategoryEntity> subCategories;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<ProductEntity> products;

}
