package com.example.GymAct.Controller;

import com.example.GymAct.DTO.AuthorizationDTO;
import com.example.GymAct.DTO.TokenResponseDTO;
import com.example.GymAct.DTO.UserDTO;
import com.example.GymAct.Models.User;
import com.example.GymAct.Repository.UserRepository;
import com.example.GymAct.Services.AuthorizationService;
import com.example.GymAct.Services.TokenServices;
import com.example.GymAct.Services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthorizationController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServices tokenServices;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthorizationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenServices.generetionToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO data){
        if(this.userRepository.findByEmail(data.email()) != null && this.userRepository.findByCpf(data.cpf()) != null){
            throw new IllegalArgumentException("Conta ja existente");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(
                data.name(),
                data.email(),
                data.cpf(),
                encryptedPassword,
                data.birth(),
                data.userType()
        );

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }


}
