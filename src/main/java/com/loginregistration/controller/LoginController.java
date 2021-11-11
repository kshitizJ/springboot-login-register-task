package com.loginregistration.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.loginregistration.model.User;
import com.loginregistration.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
            HttpSession session) {
        User user = userRepository.findUserByEmail(email).get();
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            session.setAttribute("msg", "Invalid Username Or Password");
            return "/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "redirect:/login";
    }

}
