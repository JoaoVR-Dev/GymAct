package com.example.GymAct.Configuration.User;

import com.example.GymAct.Controller.AuthorizationController;
import com.example.GymAct.DTO.UserDTO;
import com.example.GymAct.Models.UserType;
import com.example.GymAct.Services.AuthorizationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;


@Configuration
public class UserConfiguration {


    @Bean
    CommandLineRunner commandLineRunner(AuthorizationController authorizationController) {
        if (authorizationController.findByName("admin").isPresent()) {
            return null;
        } else {
            return args -> {
                UserDTO admin = new UserDTO(
                        "admin",
                        "joaovr.dev@outlook.com",
                        "123456789",
                        "45055131810",
                        LocalDate.of(1997, Month.AUGUST, 13),
                        UserType.ADMIN
                );
                UserDTO teacher = new UserDTO(
                        "teacher",
                        "teacher@gym.com",
                        "123456789",
                        "45055131811",
                        LocalDate.of(1997, Month.AUGUST, 13),
                        UserType.TEACHER
                );
                UserDTO student = new UserDTO(
                        "student",
                        "student@gym.com",
                        "123456789",
                        "45055131812",
                        LocalDate.of(1997, Month.AUGUST, 13),
                        UserType.STUDENT
                );
                authorizationController.register(admin);
                authorizationController.register(teacher);
                authorizationController.register(student);
            };
        }
    }
}
