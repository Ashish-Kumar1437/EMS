package com.example.User.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class RolesDTO {

    @Id
    @Column(name = "role_id")
    long id;

    @Column(name = "role_name")
    String roleName;
}
