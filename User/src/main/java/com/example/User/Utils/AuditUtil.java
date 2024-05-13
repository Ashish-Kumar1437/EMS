package com.example.User.Utils;

import com.example.User.Entities.AuditDTO;
import com.example.User.Enums.AuditType;
import com.example.User.Repository.AuditRepository;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDate;

@Component
@RequestScope
@AllArgsConstructor
public class AuditUtil {

    AuditRepository auditRepository;

    public static String actionBy;

    public void captureAudit(AuditType auditType,long contextualId,String action,String comment){
        audit(auditType, contextualId, action, comment, AuditUtil.actionBy);
    }

    public void captureAudit(AuditType auditType,long contextualId,String action,String comment,String actionBy){
        audit(auditType, contextualId, action, comment, actionBy);
    }

    private void audit(AuditType auditType, long contextualId, String action, String comment, String actionBy) {
        AuditDTO audit = new AuditDTO();
        audit.setAuditType(auditType);
        audit.setAuditDate(System.currentTimeMillis());
        audit.setAction(action);
        audit.setContextualId(contextualId);
        audit.setComment(comment);
        audit.setActionBy(actionBy);
        auditRepository.save(audit);
    }
}
