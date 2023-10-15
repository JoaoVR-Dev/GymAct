package com.example.GymAct.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Transient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    private TypeTraining typeTraining;
    private Integer Series;
    private Integer Repeties;
}
