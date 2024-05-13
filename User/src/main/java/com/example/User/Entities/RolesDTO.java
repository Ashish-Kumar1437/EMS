package com.example.User.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class RolesDTO {

    @Id
    @Column(name = "role_id")
    private long id;

    @NotNull(message = "RoleName can't be null")
    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;
}
