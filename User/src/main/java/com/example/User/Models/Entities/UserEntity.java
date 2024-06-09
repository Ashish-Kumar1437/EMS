package com.example.User.Models.Entities;

import com.example.User.Enums.GenderEnum;
import com.example.User.Enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long ID;

    @NotNull(message = "User Name can't be empty")
    @Column(name = "username", unique = true)
    String userName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @Column
    String name;

    @Column(name = "phone_no")
    int phoneNo;

    @Column
    String DOB;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @Enumerated(EnumType.STRING)
    RoleEnum role;

    @Column
    boolean status = true;

    @Column
    String deactivationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    List<AddressEntity> addresses = new ArrayList<>();
}
