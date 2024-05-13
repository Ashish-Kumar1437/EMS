package com.example.User.Controllers;

import com.example.User.Entities.RolesDTO;
import com.example.User.Services.RolesService;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RolesController {

    @Autowired
    RolesService rolesService;

    //Create Role
    @PostMapping("/create")
    @ResponseBody
    private String createRole(@RequestBody @Valid RolesDTO role) {
        try {
            return rolesService.CreateRole(role);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof PersistenceException) {
                return "Role already exits with this name.";
            }
            throw e;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //Fetch Roles
    @GetMapping("")
    private List<RolesDTO> getRoles() {
        return rolesService.getRoles();
    }

    //Delete Role
    @GetMapping("/delete/{roleId}")
    private String deleteRole(@PathVariable(value = "roleId") long id) throws Exception {
        return rolesService.deleteRole(id);
    }

}
