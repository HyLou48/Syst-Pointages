package com.example.gestion_pointagesemployes.Controllers;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.gestion_pointagesemployes.Repository.GroupeRepository;
import com.example.gestion_pointagesemployes.Models.Groupe;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RoutesController {

    @Autowired
    private final GroupeRepository groupeRepository;

    public RoutesController(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }

    // @GetMapping("/login")
    // public String login(@RequestParam(name = "name", required = false,
    // defaultValue = "World") String name,
    // Model model) {
    // model.addAttribute("name", name);
    // return "login";
    // }

    // @GetMapping("/")
    // public String index(@RequestParam(name = "name", required = false,
    // defaultValue = "World") String name,
    // Model model) {
    // model.addAttribute("name", name);
    // return "index";
    // }

    @PostMapping("/addGroupe")
    public String addGroupe(@ModelAttribute("groupe") Groupe groupe,
            @RequestParam("nom") String nom,
            @RequestParam("responsable") String responsable) {
        groupe.setNom(nom);
        groupe.setResponsable(responsable);
        groupeRepository.save(groupe);
        return "groupe";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Optional<Groupe> findOne(Long id) {
        return groupeRepository.findById(id);
    }

}
