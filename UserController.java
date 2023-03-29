package com.example.gestion_pointagesemployes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestion_pointagesemployes.Models.User;
import com.example.gestion_pointagesemployes.Repository.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "signup";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute("utilisateur") User user) {
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

}
