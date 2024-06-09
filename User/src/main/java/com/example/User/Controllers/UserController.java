package com.example.User.Controllers;

import com.example.User.Models.DTO.LoginRequestDTO;
import com.example.User.Models.Entities.UserEntity;
import com.example.User.Models.DTO.CreateUserDTO;
import com.example.User.Services.UserService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(originPatterns = "*")
public class UserController {

    @Autowired
    private UserService userService;


    //Create User
    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof PersistenceException) {
                return ResponseEntity.badRequest().body("Username or email already exists.");
            }
            throw e;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //update User -> deactivate/Activate User
    @PostMapping("/update")
    private UserEntity updateUser(@RequestBody UserEntity user, @RequestParam boolean isActivate, @RequestParam boolean isDeactivate) throws Exception {
        return userService.updateUser(user, isActivate, isDeactivate);
    }

    //Fetch User
    @GetMapping("")
    public UserEntity fetchUser(@RequestParam String userName) throws Exception {
        return userService.fetchUser(userName);
    }

    //Reset Password
    @GetMapping("/resetPassword")
    public String resetPassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam long userId) throws Exception {
        return userService.resetPassord(oldPassword, newPassword, userId);
    }

    //Forgot Password
    @GetMapping("/forgot/{email}")
    public String forgotPassword(@PathVariable(value = "email") String email) throws Exception {
        return userService.forgotPassword(email);
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request, HttpServletResponse response){
        String token = userService.login(request);

        Cookie cookie = new Cookie("Auth-token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/bytoken")
    public UserEntity getUserBYToken(HttpServletRequest request) throws Exception {
        return userService.getUserByToken(request);
    }

    @GetMapping("/email-exits")
    public boolean doesEmailExits(@RequestParam String email){
        return userService.doesEmailExits(email);
    }

    @GetMapping("/username-exits")
    public boolean doesUserNameExits(@RequestParam String username){
        return userService.doesUserName(username);
    }

}
