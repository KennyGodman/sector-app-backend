package com.sector.service;

import com.sector.model.User;
import com.sector.repository.UserRepository;
import com.sector.request.LoginRequest;
import com.sector.request.UserRegistrationRequest;
import com.sector.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRegistrationRequest request){
        User foundUser = getUserByEmail(request.getEmail());
        if(foundUser != null){
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = saveUser(user);
        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .build();
    }


    public UserResponse login (LoginRequest loginRequest){
        User foundUser = getUserByEmail(loginRequest.getEmail());
        if(foundUser == null){
            throw new RuntimeException("User not found");
        }

        if(!passwordEncoder.matches(loginRequest.getPassword(), foundUser.getPassword())){
            throw new RuntimeException("Password or Email is incorrect");
        }

        return UserResponse.builder()
                .id(foundUser.getId())
                .email(foundUser.getEmail())
                .name(foundUser.getName())
                .build();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }
}
