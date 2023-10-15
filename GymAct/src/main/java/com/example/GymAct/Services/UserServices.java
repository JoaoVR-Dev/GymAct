package com.example.GymAct.Services;

import com.example.GymAct.DTO.UserDTO;
import com.example.GymAct.Models.User;
import com.example.GymAct.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity findAll(){
        List<UserDTO> userList = userRepository.findAll().stream().map(UserDTO::new).toList();

        return ResponseEntity.ok(userList);
    }

    public Boolean DeleteUser(String email){
        if(userRepository.findByEmail(email) != null){
            return false;
        }else{
            UserDetails userDetails = userRepository.findByEmail(email);
            userRepository.delete((User)userDetails);
            return true;
        }
    }
}
