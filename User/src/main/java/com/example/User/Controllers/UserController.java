package com.example.User.Controllers;

import com.example.User.Entities.UserDTO;
import com.example.User.Services.UserService;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO user) throws Exception {
        try{
            return ResponseEntity.ok(userService.createUser(user));
        }catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof PersistenceException) {
                return ResponseEntity.badRequest().body("Username or email already exists.");
            }
            throw e;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/resetPassword")
    public String resetPassword(@RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam long userId) throws Exception {
        return userService.resetPassord(oldPassword,newPassword,userId);
    }

    @GetMapping("")
    public UserDTO fetchUser(@RequestParam long id) throws Exception {
        return userService.fetchUser(id);
    }
}
