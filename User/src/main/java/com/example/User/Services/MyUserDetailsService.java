package com.example.User.Services;

import com.example.User.Entities.UserDTO;
import com.example.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository  userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = Optional.ofNullable(userRepository.findByUserName(username)).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }

}
