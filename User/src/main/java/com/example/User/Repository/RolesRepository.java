package com.example.User.Repository;

import com.example.User.Entities.RolesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesDTO,Long> {
}
