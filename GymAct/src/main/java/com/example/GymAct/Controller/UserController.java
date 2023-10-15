package com.example.GymAct.Controller;

import com.example.GymAct.Models.User;
import com.example.GymAct.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/list")
    public ResponseEntity findAll(){
        return userServices.findAll();
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity deleteUser(@PathVariable String email){
        if(userServices.DeleteUser(email)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
