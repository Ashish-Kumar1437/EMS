package com.Product.Models.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "variants")
@Data
public class VariantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(nullable = false)
    Double prize;

    @Column(nullable = false)
    Long qty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    ProductEntity product;

    @ManyToMany
    @JoinTable(
            name = "variant_attributes",
            joinColumns = @JoinColumn(name = "variant_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    List<AttributeEntity> attributes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tags")
    @JsonManagedReference
    TagEntity tags;
}
