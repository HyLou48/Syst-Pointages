package com.example.gestion_pointagesemployes.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.gestion_pointagesemployes.Models.User;
import com.example.gestion_pointagesemployes.Models.Login;
import com.example.gestion_pointagesemployes.Models.Pointages;
import com.example.gestion_pointagesemployes.Models.Groupe;

import com.example.gestion_pointagesemployes.Repository.PointagesRepository;
import com.example.gestion_pointagesemployes.Repository.GroupeRepository;
import com.example.gestion_pointagesemployes.Repository.UserRepository;

@Controller
public class LoginController {
    @Autowired
    private PointagesRepository employeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private PointagesRepository pointagesRepository;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "login";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

    /*
     * @PostMapping("/login")
     * public String processLoginForm(@ModelAttribute("utilisateur") Utilisateur
     * utilisateur, BindingResult result,
     * Model model) {
     * Utilisateur user =
     * utilisateurService.findByEmail(utilisateur.getEmailUtilisateur());
     * if (user == null ||
     * !user.getMotDePasse().equals(utilisateur.getMotDePasse())) {
     * result.rejectValue("emailUtilisateur", "error.user",
     * "Invalid email or password");
     * return "login";
     * }
     * 
     * }
     */

    // @GetMapping("/employe")
    // public String showEmployeDashboard(Model model) {
    // model.addAttribute("title", "Employe Dashboard");
    // return "employe";
    // }

    // @PostMapping("/employe")
    // public String ajouterEmploye(@ModelAttribute("employe") Employe employe) {
    // // EmployeRepository.save(employe);
    // return "redirect:/employes";
    // }

    @GetMapping("/pointages")
    public String showPointagesDashboard(Model model) {
        model.addAttribute("title", "Pointages Dashboard");
        List<Pointages> pointages = employeRepository.findAll();
        model.addAttribute("pointages", pointages);
        return "pointages";
    }

    @GetMapping("/horaire")
    public String showHoraireDashboard(Model model) {
        model.addAttribute("title", "Horaire Dashboard");
        return "horaire";
    }

    @GetMapping("/groupe")
    public String showGroupeDashboard(Model model) {
        model.addAttribute("title", "Groupe Dashboard");
        List<Groupe> groupe = groupeRepository.findAll();
        model.addAttribute("groupes", groupe);
        return "groupe";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "utilisateur") Login login, Model model) {
        String password = login.getPassword();
        String email = login.getEmail();

        User user = userRepository.findByEmail(email);
        // User userpass = userRepository.findByMot_de_passe(password);

        if (user == null) {
            return "login";
        }

        model.addAttribute("utilisateur", true);
        return "dashboard";
    }

    @PostMapping("/save_hours")
    public String saveHours(@ModelAttribute("horaire") Pointages pointage) {
        pointagesRepository.save(pointage);
        return "pointages";
    }
}
