package com.Product.Models.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "sub_category")
@Data
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    CategoryEnitity category;

    @OneToMany(mappedBy = "subCategory")
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<ProductEntity> products;

}
