package com.example.User.Services;

import com.example.User.Models.DTO.LoginRequestDTO;
import com.example.User.Models.Entities.UserEntity;
import com.example.User.Models.DTO.CreateUserDTO;
import com.example.User.Enums.AuditType;
import com.example.User.Repository.UserRepository;
import com.example.User.Utils.AuditUtil;
import com.example.User.Utils.EmailUtil;
import com.example.User.Utils.JwtUtil;
import com.example.User.Utils.ObjectComparatorUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private AuthenticationManager manager;
    private JwtUtil jwtUtil;
    private EmailUtil emailUtil;
    private AuditUtil auditUtil;

    public UserEntity createUser(CreateUserDTO user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        UserEntity profile = new UserEntity();
        profile.setPassword(hashPassword);
        profile.setUserName(user.getUserName());
        profile.setEmail(user.getEmail());
        profile = userRepository.save(profile);
        auditUtil.captureAudit(AuditType.USER,profile.getID(),"User created","","System");
        //emailUtil.sendMail(profile.getEmail(),"HI","Test");
        return profile;
    }

    public String resetPassord(String oldPassword, String newPassword, long userId) throws Exception {
        try {
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                auditUtil.captureAudit(AuditType.USER,user.getID(),"Password Changed","","System");
            } else {
                return "Password Incorrect";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "Password Changed Successfully";
    }

    public UserEntity fetchUser(String userName) throws Exception {
        UserEntity user = Optional.ofNullable(userRepository.findByUserName(userName)).orElseThrow(() -> new Exception("User not present"));
        user.setPassword(null);
        return user;
    }

    public UserEntity updateUser(UserEntity newUser, boolean isActivate, boolean isDeactivate) throws Exception {
        UserEntity oldUser = userRepository.findById(newUser.getID()).orElseThrow(() -> new Exception("User not present"));

        if (!oldUser.isStatus() && !isActivate) {
            throw new Exception("Deactivated User can't be modified");
        }

        newUser.setPassword(oldUser.getPassword());
        activateUserIfNeeded(newUser, isActivate);
        deactivateUserIfNeeded(newUser, isDeactivate, oldUser);
        String changes= ObjectComparatorUtil.compareObject(oldUser, newUser);

        if(changes != null){
            auditUtil.captureAudit(AuditType.USER,newUser.getID(),changes,"");
        }

        return userRepository.save(newUser);
    }

    private void activateUserIfNeeded(UserEntity newUser, boolean isActivate) {
        if (isActivate) {
            newUser.setStatus(true);
            newUser.setDeactivationDate(null);
        }
    }

    private void deactivateUserIfNeeded(UserEntity newUser, boolean isDeactivate, UserEntity oldUser) throws Exception {
        if (!isDeactivate || newUser.getDeactivationDate() == null) {
            return;
        }

        if (isDeactivationDateToday(newUser.getDeactivationDate())) {
            newUser.setStatus(false);
        }
    }

    private boolean isDeactivationDateToday(String deactivationDateStr) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate deactivationDate = LocalDate.parse(deactivationDateStr, dtf);
        if (deactivationDate.isBefore(LocalDate.now())) {
            throw new Exception("Deactivation date can't be a past date.");
        }
        return deactivationDate.equals(LocalDate.now());
    }

    public String forgotPassword(String email) throws Exception {
        UserEntity user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new Exception("No User present with this email"));
        //emailUtil.sendMail(user.getEmail(), "Reset Password", "Reset Password");
        return "Email Sent";
    }

    public String login(LoginRequestDTO request) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        try {
            manager.authenticate(auth);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

        return jwtUtil.generateToken(auth.getName());
    }

    public UserEntity getUserByToken(HttpServletRequest request) throws Exception {
        String token = jwtUtil.extractToken(request);
        if(token == null) {
            throw new Exception("Invalid Request");
        }
        String userName = jwtUtil.extractUsername(token);
        return fetchUser(userName);
    }

    public boolean doesEmailExits(String email){
    return userRepository.existsByEmail(email);
    }

    public boolean doesUserName(String userName){
        return userRepository.existsByUserName(userName);
    }
}
