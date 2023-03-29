package com.example.gestion_pointagesemployes.Controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.gestion_pointagesemployes.Models.Departement;

import com.example.gestion_pointagesemployes.Repository.DepartementRepository;


@Controller
public class DepartementController {

    @Autowired
    private final DepartementRepository departementRepository;

    public DepartementController(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @GetMapping("/departement")
    public String listDepartements(Model model) {
        List<Departement> departements = departementRepository.findAll();
        model.addAttribute("departements", departements);
        // model.addAttribute("departementForm", new departementForms());
        return "departement";
    }

    // @PostMapping("/addDep")
    // public String addDepartement(@ModelAttribute("departementForm") @Validated departementForms form,
    //         BindingResult bindingResult,
    //         RedirectAttributes redirectAttributes) {
    //     if (bindingResult.hasErrors()) {
    //         return "departement";
    //     }
    //     Departement departement = new Departement();
    //     departement.setNom(form.getNom());
    //     departement.setResponsable(form.getResponsable());
    //     System.out.println("Nom du département : " + departement.getNom());
    //     System.out.println("Responsable du département : " + departement.getResponsable());
    //     try {
    //         departementRepository.save(departement);
    //         redirectAttributes.addFlashAttribute("successMessage", "Département ajouté avec succès!");
    //     } catch (Exception e) {
    //         redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de l'ajout du département.");
    //         System.out.println(e);
    //     }
    //     return "redirect:/departement";
    // }

    @PostMapping("/addDep")
    public String addEmploye(@ModelAttribute("departement") Departement departement,
            @RequestParam("nom") String nom,
            @RequestParam("responsable") String responsable) {
        departement.setNom(nom);
        departement.setResponsable(responsable);
        departementRepository.save(departement);
        return "dashboard";
    }
}
