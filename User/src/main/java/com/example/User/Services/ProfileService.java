package com.example.User.Services;

import com.example.User.Entities.ProfileDTO;
import com.example.User.Entities.UserDTO;
import com.example.User.Repository.ProfileRepository;
import com.example.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProfileService {

    ProfileRepository profileRepository;

    UserRepository userRepository;

    public String createProfile(ProfileDTO profile,long userId) throws Exception {
        UserDTO user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not Present"));
        if(!Objects.isNull(user.getProfile())){
            throw new Exception("User already exits");
        }
        profile.setUser(user);
        profileRepository.save(profile);
        return "Profile Created Successfully";
    }

    public ProfileDTO fetchProfile(long id) throws Exception {
        return profileRepository.findById(id).orElseThrow(() -> new Exception("Profile not exist"));
    }

}
