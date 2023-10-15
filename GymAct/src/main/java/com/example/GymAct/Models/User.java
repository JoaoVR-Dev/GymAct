package com.example.GymAct.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(length = 11,unique = true, nullable = false)
    private String cpf;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Transient
    private Integer age;
    private LocalDate birth;
    private LocalDate cadastrationDate;
    private UserType userType;

    public User(String name, String email,String cpf, String password, LocalDate birth, UserType userType) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.birth = birth;
        this.cadastrationDate = LocalDate.now();
        this.userType = userType;
    }
    public Integer getAge(){
        return Period.between(birth, LocalDate.now()).getYears();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userType == userType.ADMIN){return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_TEACHER"), new SimpleGrantedAuthority("TYPE_STUDENT"));}
        else if(this.userType == userType.TEACHER){return List.of(new SimpleGrantedAuthority("ROLE_TEACHER"), new SimpleGrantedAuthority("ROLE_STUDENT"));}
        else{return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));}
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
