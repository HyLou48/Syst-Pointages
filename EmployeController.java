package com.example.gestion_pointagesemployes.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestion_pointagesemployes.Models.Employe;
import com.example.gestion_pointagesemployes.Models.Departement;
import com.example.gestion_pointagesemployes.Models.Groupe;
import com.example.gestion_pointagesemployes.Repository.EmployeRepository;
import com.example.gestion_pointagesemployes.Repository.DepartementRepository;

import com.example.gestion_pointagesemployes.Repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class EmployeController {
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private GroupeRepository groupeRepository;

    // Get all employees
    @GetMapping("/employe")
    public String getAllEmployes(Model model) {
        List<Employe> employes = employeRepository.findAll();
        model.addAttribute("employees", employes);

        List<Groupe> optionGroupe = groupeRepository.findAll();
        model.addAttribute("optionsGroup", optionGroupe);

        List<Departement> optionsDep = departementRepository.findAll();
        model.addAttribute("optionsDep", optionsDep);
        return "employe";
    }

    @GetMapping("/employeFiltered")
    public String getEmployeFiltered(Model model) {
        List<Employe> employes = employeRepository.findAll();
        model.addAttribute("employees", employes);

        List<Groupe> optionGroupe = groupeRepository.findAll();
        model.addAttribute("optionsGroup", optionGroupe);

        List<Departement> optionsDep = departementRepository.findAll();
        model.addAttribute("optionsDep", optionsDep);
        return "employeFiltered";
    }

    @PostMapping("/add_employee")
    public String addEmploye(@ModelAttribute("employe") Employe employe,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email) {
        employe.setNom(nom);
        employe.setPrenom(prenom);
        employe.setEmail(email);
        employeRepository.save(employe);
        return "dashboard";
    }

    // Get an employee by ID
    @GetMapping("/employe/{id}")
    public String getEmployeById(@PathVariable Long id, Model model) {
        // Optional<Employe> employe = employeRepository.findById(id);
        // employe.ifPresent(e -> model.addAttribute("employe", e));
        return "employe-details";
    }

    // Update an existing employee
    @PostMapping("/updateEmploye/{id}")
    public String updateEmploye(@PathVariable Long id, @ModelAttribute("employe") Employe employe,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            employe.setId(id);
            return "employe-form";
        }
        // EmployeRepository.save(employe);
        model.addAttribute("employes", employeRepository.findAll());
        return "redirect:/";
    }

    // Delete an employee
    @GetMapping("/deleteEmploye/{id}")
    public String deleteEmploye(@PathVariable Long id, Model model) {
        employeRepository.deleteById(id);
        model.addAttribute("employes", employeRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) throws SQLException {
        // Connexion à la base de données
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionpointages", "root", "");

        // Requête SQL pour récupérer le nombre d'employés
        String sql = "SELECT COUNT(*) as nb_employes FROM employe";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // Récupération du nombre d'employés
        int nbEmployes = 0;
        if (rs.next()) {
            nbEmployes = rs.getInt("nb_employes");
        }

        // Transmission du nombre d'employés à la vue
        model.addAttribute("nbEmployes", nbEmployes);

        return "index";
    }

}
