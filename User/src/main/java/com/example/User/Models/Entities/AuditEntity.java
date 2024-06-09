package com.example.User.Models;

import com.example.User.Enums.AuditType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "audit")
public class AuditDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    AuditType auditType;

    @Column
    long contextualId;

    @Column
    String action;

    @Column(name = "by")
    String actionBy;

    @Column(name = "date")
    long auditDate;

    @Column
    String comment;
}


//Audit type
//Which user audit