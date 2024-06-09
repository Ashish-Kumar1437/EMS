package com.example.User.Repository;

import com.example.User.Models.Entities.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity,Long> {
}
