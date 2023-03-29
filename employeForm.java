package com.example.gestion_pointagesemployes.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class employeForm {
    private String email;
    private String nom;
    private String prenom;
}
