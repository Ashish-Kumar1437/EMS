package com.example.User.Services;

import com.example.User.Entities.UserDTO;
import com.example.User.Repository.UserRepository;
import com.example.User.Utils.EmailUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    private EmailUtil emailUtil;
    public UserDTO createUser(UserDTO user){
         String hashPassword = passwordEncoder.encode(user.getPassword());
         user.setPassword(hashPassword);
         emailUtil.sendMail(user.getEmail(),"HI","Test");
         return userRepository.save(user);
    }

    public String resetPassord(String oldPassword,String newPassword,long userId) throws Exception {

        try{
            UserDTO user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
            if(passwordEncoder.matches(oldPassword, user.getPassword())){
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
            }else{
                return "Password Incorrect";
            }
        }catch (Exception e){
            return e.getMessage();
        }

        return "Password Changed Successfully";
    }

    public UserDTO fetchUser(long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User doesn't exist"));
    }
}
