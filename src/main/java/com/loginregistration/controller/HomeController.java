package com.loginregistration.controller;

import com.loginregistration.model.User;
import com.loginregistration.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping({ "/", "/home" })
    public String home(@ModelAttribute("user") User user, ModelMap map) {
        if (user != null) {
            map.addAttribute("user", user);
        } else {
            map.addAttribute("user", null);
        }
        return "index";
    }

    @RequestMapping(value = { "/", "home" }, method = RequestMethod.POST)
    public String home1(@ModelAttribute("user") User user, ModelMap map) {
        map.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "profile";
    }

}
