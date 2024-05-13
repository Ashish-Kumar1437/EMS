package com.example.User.Repository;

import com.example.User.Entities.AuditDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditDTO,Long> {
}
