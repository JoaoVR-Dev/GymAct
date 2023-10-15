package com.example.GymAct.DTO;

import com.example.GymAct.Models.User;
import com.example.GymAct.Models.UserType;

import java.time.LocalDate;

public record UserDTO(String name,
                      String email,
                      String password,
                      String cpf,
                      LocalDate birth,
                      UserType userType) {

    public UserDTO(User user){
        this(user.getName(), user.getEmail(), user.getPassword(),user.getCpf(),user.getBirth(),user.getUserType());
    }

}
