package com.example.User.Controllers;

import com.example.User.Entities.ProfileDTO;
import com.example.User.Services.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    ProfileService profileService;

    @PostMapping(value = "/create")
    public String createProfile(@RequestBody ProfileDTO profile, @RequestParam long userId) throws Exception {
        return profileService.createProfile(profile,userId);
    }

    @GetMapping(value = "")
    public ProfileDTO fetchProfile(@RequestParam long id) throws Exception {
        return profileService.fetchProfile(id);
    }
}
