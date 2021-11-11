package com.loginregistration.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.loginregistration.model.User;
import com.loginregistration.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
// import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model, HttpServletRequest request)
            throws ServletException {   
        userRepository.save(user);
        model.addAttribute("user", user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
            RedirectAttributes redirectAttributes) {
        User user = userRepository.findUserByEmail(email).get();
        if (user != null) {
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:home1";
        } else {
            redirectAttributes.addAttribute("msg", "Invalid Username or Password!!!");
            return "/login";
        }
    }

    @GetMapping("/logout/{user}")
    public String logout(@PathVariable User user, ModelMap model) {
        model.remove("user", user);
        return "redirect:/login";
    }

}
