package com.example.GymAct.Configuration.Security;

import com.example.GymAct.Models.User;
import com.example.GymAct.Models.UserType;
import com.example.GymAct.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;

@Configuration
public class UserConfiguration {


    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        if (repository.findByName("admin").isPresent()) {
            return null;
        } else {
            return args -> {
                User admin = new User(
                        "admin",
                        "joaovr.dev@outlook.com",
                        "123456789",
                        LocalDate.of(1997, Month.AUGUST, 13),
                        UserType.ADMIN
                );
                User teacher = new User(
                        "teacher",
                        "teacher@gym.com",
                        "123456789",
                        LocalDate.of(1997, Month.AUGUST, 13),
                        UserType.TEACHER
                );
                User student = new User(
                        "student",
                        "student@gym.com",
                        "123456789",
                        LocalDate.of(1997, Month.AUGUST, 13),
                        UserType.STUDENT
                );
                repository.saveAll(
                        List.of(admin, teacher, student)
                );
            };
        }
    }
}
