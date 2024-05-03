package com.example.User.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "profiles")
@Data
public class ProfileDTO {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    UserDTO user;

    String name;

    @Column(name = "phone_no")
    int phoneNo;

    String DOB;

    @ManyToOne
    @JoinColumn(name = "role_id")
    RolesDTO role;
}

