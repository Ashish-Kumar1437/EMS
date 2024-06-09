package com.example.User.Utils;

import com.example.User.Models.Entities.AuditEntity;
import com.example.User.Enums.AuditType;
import com.example.User.Repository.AuditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@AllArgsConstructor
public class AuditUtil {

    AuditRepository auditRepository;

    public static String actionBy;

    public void captureAudit(AuditType auditType, long contextualId, String action, String comment){
        audit(auditType, contextualId, action, comment, AuditUtil.actionBy);
    }

    public void captureAudit(AuditType auditType, long contextualId, String action, String comment, String actionBy){
        audit(auditType, contextualId, action, comment, actionBy);
    }

    private void audit(AuditType auditType, long contextualId, String action, String comment, String actionBy) {
        AuditEntity audit = new AuditEntity();
        audit.setAuditType(auditType);
        audit.setAuditDate(System.currentTimeMillis());
        audit.setAction(action);
        audit.setContextualId(contextualId);
        audit.setComment(comment);
        audit.setActionBy(actionBy);
        auditRepository.save(audit);
    }
}
