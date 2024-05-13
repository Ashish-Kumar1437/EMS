package com.example.User.Services;

import com.example.User.Constants.Roles;
import com.example.User.Entities.LoginRequestDO;
import com.example.User.Entities.UserDTO;
import com.example.User.Entities.CreateUserDO;
import com.example.User.Enums.AuditType;
import com.example.User.Repository.UserRepository;
import com.example.User.Utils.AuditUtil;
import com.example.User.Utils.EmailUtil;
import com.example.User.Utils.JwtUtil;
import com.example.User.Utils.ObjectComparatorUtil;
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

    public UserDTO createUser(CreateUserDO user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        UserDTO profile = new UserDTO();
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
            UserDTO user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
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

    public UserDTO fetchUser(String userName) throws Exception {
        UserDTO user = Optional.ofNullable(userRepository.findByUserName(userName)).orElseThrow(() -> new Exception("User not present"));
        user.setPassword(null);
        return user;
    }

    public UserDTO updateUser(UserDTO newUser, boolean isActivate, boolean isDeactivate) throws Exception {
        UserDTO oldUser = userRepository.findById(newUser.getID()).orElseThrow(() -> new Exception("User not present"));

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

    private void activateUserIfNeeded(UserDTO newUser, boolean isActivate) {
        if (isActivate) {
            newUser.setStatus(true);
            newUser.setDeactivationDate(null);
        }
    }

    private void deactivateUserIfNeeded(UserDTO newUser, boolean isDeactivate, UserDTO oldUser) throws Exception {
        if (!isDeactivate || newUser.getDeactivationDate() == null) {
            return;
        }

        if (oldUser.getRole() != null && oldUser.getRole().getId() == Roles.ADMIN) {
            throw new Exception("Admin user can't be deactivated");
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
        UserDTO user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new Exception("No User present with this email"));
        //emailUtil.sendMail(user.getEmail(), "Reset Password", "Reset Password");
        return "Email Sent";
    }

    public String login(LoginRequestDO request) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        try {
            manager.authenticate(auth);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

        return jwtUtil.generateToken(auth.getName());
    }

    public UserDTO getUserByToken(String token) throws Exception {
        String userName = jwtUtil.extractUsername(token);
        return fetchUser(userName);
    }
}
