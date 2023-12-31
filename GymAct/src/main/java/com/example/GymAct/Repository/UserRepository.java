package com.example.GymAct.Repository;

import com.example.GymAct.Models.User;
import org.hibernate.query.criteria.JpaRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByEmail(String email);
    Optional<User> findByName(String name);
    UserDetails findByCpf(String cpf);
}
