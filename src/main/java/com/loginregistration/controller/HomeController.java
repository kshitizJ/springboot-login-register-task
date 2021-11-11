package com.loginregistration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.loginregistration.model.User;
import com.loginregistration.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping({ "/", "/home" })
    public String home(HttpSession session) {
        Object user = session.getAttribute("user");
        System.out.println("\n\n\n" + user + "\n\n\n");
        return "index";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return "profile";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/updateUser")
    public String updateProfile(@ModelAttribute("user") User user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User setUser = userService.saveUser(user);
        session.setAttribute("user", setUser);
        return "redirect:/";
    }

}
