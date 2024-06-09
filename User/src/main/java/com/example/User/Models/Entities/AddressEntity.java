package com.example.User.Models.Entities;

import com.example.User.Enums.AddressType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "reciver_name")
    String receiverName;

    @Column(name = "reciver_phone")
    Long receiverPhoneNo;

    @Column
    String address;

    @Column
    String city;

    @Column
    String state;

    @Column
    String landmark;

    @Column
    Long pincode;

    @Column(name = "address_type")
    @Enumerated(EnumType.STRING)
    AddressType addressType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    UserEntity user;
}
