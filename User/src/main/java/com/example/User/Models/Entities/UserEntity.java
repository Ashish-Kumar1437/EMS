package com.example.User.Models;

import com.example.User.Enums.GenderEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@Table(name = "users")
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    long ID;

    @NotNull(message = "User Name can't be empty")
    @Column(name = "username",unique = true)
    String userName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(unique = true,nullable = false)
    String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;

    @Column
    String name;

    @Column(name = "phone_no")
    int phoneNo;

    @Column
    String DOB;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @Column
    boolean status = true;


    @Column
    String deactivationDate;

    @Column
    String address;

    @Column
    String city;

    @Column
    String state;

    @Column
    Long pincode;
}
