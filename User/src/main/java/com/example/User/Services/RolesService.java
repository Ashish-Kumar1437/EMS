package com.example.User.Services;

import com.example.User.Entities.RolesDTO;
import com.example.User.Enums.AuditType;
import com.example.User.Repository.RolesRepository;
import com.example.User.Utils.AuditUtil;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    AuditUtil auditUtil;

    public String CreateRole(RolesDTO role) {
        if (rolesRepository.findById(role.getId()).isPresent()) {
            return "Role already present with this ID";
        }
        rolesRepository.save(role);
        auditUtil.captureAudit(AuditType.ROLE,role.getId(),"Role created","");
        return "Role created successfully";
    }

    public List<RolesDTO> getRoles() {
        return rolesRepository.findAll();
    }

    public String deleteRole(long id) throws Exception {
        RolesDTO role = rolesRepository.findById(id).orElseThrow(() -> new Exception(String.format("Role doesn't exit with %s id", id)));

        //Check whether any user exits with this role

        rolesRepository.deleteById(id);
        auditUtil.captureAudit(AuditType.ROLE,role.getId(),"Role Deleted","");
        return "Role deleted successfully";
    }
}
