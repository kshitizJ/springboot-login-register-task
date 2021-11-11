package com.loginregistration.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.loginregistration.model.User;
import com.loginregistration.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

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

}
